package de.gobrother.network.packet;

import lombok.Getter;
import lombok.Setter;

import javax.crypto.SecretKey;
import java.io.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public abstract class Packet {

    public static PrivateKey RSAPrivateKey;
    public static PublicKey RSAPublicKey;

    static {
        try {
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
            gen.initialize(1024);
            KeyPair pair = gen.generateKeyPair();
            RSAPrivateKey = pair.getPrivate();
            RSAPublicKey = pair.getPublic();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static String getServerIdHash(String serverId, SecretKey secretKey) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(serverId.getBytes());
            md.update(secretKey.getEncoded());
            md.update(RSAPublicKey.getEncoded());

            return new BigInteger(md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @ReaderMethod
    private boolean readBoolean(McInputStream input) throws IOException {
        return input.readBoolean();
    }

    @ReaderMethod(Field.Type.VarInt)
    private int readVarInt(McInputStream input) throws IOException {
        return input.readVarInt();
    }

    @WriterMethod(Field.Type.VarInt)
    private void writeVarInt(McOutputStream output, int value) throws IOException {
        output.writeVarInt(value);
    }

    @ReaderMethod(Field.Type.UnsignedByte)
    private int readUnsignedInt(McInputStream input) throws IOException {
        return input.readUnsignedByte();
    }

    @WriterMethod(Field.Type.UnsignedByte)
    private void writeUnsignedByte(McOutputStream output, int value) throws IOException {
        output.writeByte((byte) (value & 0xFF));
    }

    @ReaderMethod(Field.Type.UnsignedShort)
    private int readUnsignedShort(McInputStream input) throws IOException {
        return input.readUnsignedShort();
    }

    @WriterMethod(Field.Type.UnsignedShort)
    private void writeUnsignedShort(McOutputStream output, int value) throws IOException {
        output.writeShort((short) (value & 0xffff));
    }

    @ReaderMethod
    private String readString(McInputStream input) throws IOException {
        byte[] data = new byte[input.readVarInt()];
        input.readFully(data);
        return new String(data);
    }

    @WriterMethod
    private void writeString(McOutputStream output, String value) throws IOException {
        output.writeVarInt(value.length());
        output.writeBytes(value);
    }

    @ReaderMethod
    private byte readByte(McInputStream input) throws IOException {
        return input.readByte();
    }

    @WriterMethod
    private void writeByte(McOutputStream output, byte value) throws IOException {
        output.writeByte(value);
    }

    @ReaderMethod
    private int readInt(McInputStream input) throws IOException {
        return input.readInt();
    }

    @WriterMethod
    private void writeInt(McOutputStream output, int value) throws IOException {
        output.writeInt(value);
    }

    @ReaderMethod
    private double readDouble(McInputStream input) throws IOException {
        return input.readDouble();
    }

    @WriterMethod
    private void writeDouble(McOutputStream output, double value) throws IOException {
        output.writeDouble(value);
    }

    @WriterMethod
    private void writeBoolean(McOutputStream output, boolean value) throws IOException {
        output.writeBoolean(value);
    }

    @ReaderMethod
    private float readFloat(McInputStream input) throws IOException {
        return input.readFloat();
    }

    @WriterMethod
    private void writeFloat(McOutputStream output, float value) throws IOException {
        output.writeFloat(value);
    }

    @ReaderMethod
    private long readLong(McInputStream input) throws IOException {
        return input.readLong();
    }

    @WriterMethod
    private void writeLong(McOutputStream output, long value) throws IOException {
        output.writeLong(value);
    }

    @ReaderMethod
    private byte[] readByteArray(McInputStream input) throws IOException {
        byte[] ret = new byte[input.readVarInt()];
        input.readFully(ret);
        return ret;
    }

    @WriterMethod
    private void writeByteArray(McOutputStream output, byte[] value) throws IOException {
        output.writeVarInt(value.length);
        output.write(value);
    }

    @ReaderMethod(Field.Type.FixedPoint)
    private float readFixedPoint(McInputStream input) throws IOException {
        return ((float) input.readInt()) / 32;
    }

    @WriterMethod(Field.Type.Position)
    private void writePosition(McOutputStream output, int x, int y, int z) throws IOException {
        output.writeLong( ((x & 0x3FFFFFF) << 38) | ((y & 0xFFF) << 26) | (z & 0x3FFFFFF));
    }

    @ReaderMethod(Field.Type.Position)
    private long readPosition(McInputStream input) throws IOException {
        long val = input.readLong();

        int x = (int) (val >> 38);
        int y = (int) ((val >> 26) & 0xFFF);
        int z = (int) (val << 38 >> 38);

        return x | y | z;
    }

    public void read(McInputStream input) throws IOException {
        Arrays.stream(this.getClass().getDeclaredFields()).filter((f) -> f.isAnnotationPresent(Field.class)).sorted(Comparator.comparing((f) -> f.getAnnotation(Field.class).value())).forEachOrdered((f) -> {
            try {
                Method reader = getAllMethods(getClass()).stream().filter((m) -> m.isAnnotationPresent(ReaderMethod.class)).filter((m) -> m.getReturnType().equals(f.getType()) && m.getAnnotation(ReaderMethod.class).value() == f.getAnnotation(Field.class).type()).limit(1).collect(Collectors.toList()).get(0);
                reader.setAccessible(true);
                f.set(Packet.this, reader.invoke(Packet.this, input));
            } catch (IndexOutOfBoundsException e) {
                throw new RuntimeException("No ReaderMethod available for " + f.getAnnotation(Field.class).type() + ", " + f.getType());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void write(McOutputStream output) throws IOException {
        Arrays.stream(this.getClass().getDeclaredFields()).filter((f) -> f.isAnnotationPresent(Field.class)).sorted(Comparator.comparing((f) -> f.getAnnotation(Field.class).value())).forEachOrdered((f) -> {
            try {
                Method writer = getAllMethods(getClass()).stream().filter((m) -> m.isAnnotationPresent(WriterMethod.class)).filter((m) -> m.getParameterTypes()[1].equals(f.getType()) && m.getAnnotation(WriterMethod.class).value() == f.getAnnotation(Field.class).type()).limit(1).collect(Collectors.toList()).get(0);
                writer.setAccessible(true);
                writer.invoke(Packet.this, output, f.get(Packet.this));
            } catch (IndexOutOfBoundsException e) {
                throw new RuntimeException("No WriterMethod available for " + f.getAnnotation(Field.class).type() + ", " + f.getType());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private List<Method> getAllMethods(Class<?> clazz) {
        List<Method> ret = new ArrayList<>();
        if (!clazz.equals(Object.class)) {
            ret.addAll(getAllMethods(clazz.getSuperclass()));
        }
        Arrays.stream(clazz.getDeclaredMethods()).forEach((m) -> ret.add(m));
        return ret;
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    protected @interface ReaderMethod {
        Field.Type value() default Field.Type.Auto;
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    protected @interface WriterMethod {
        Field.Type value() default Field.Type.Auto;
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Field {

        int value();

        Type type() default Type.Auto;

        enum Type {
            Auto,
            Byte,
            UnsignedByte,
            Short,
            UnsignedShort,
            Int,
            VarInt,
            FixedPoint,
            Position
        }
    }

    public static class Protocol {

        private HashMap<Integer, Class<? extends Packet>> ClientPackets = new HashMap<>();
        private HashMap<Integer, Class<? extends Packet>> ServerPackets = new HashMap<>();
        @Getter
        @Setter
        private int compressionTreshold = -1;

        public Protocol registerClientPacket(int id, Class<? extends Packet> clazz) {
            ClientPackets.put(id, clazz);
            return this;
        }

        public Protocol registerServerPacket(int id, Class<? extends Packet> clazz) {
            ServerPackets.put(id, clazz);
            return this;
        }

        public Packet getPacketById(int id) {
            try {
                return ClientPackets.get(Integer.valueOf(id)).newInstance();
            } catch (NullPointerException e) {
                throw new RuntimeException("Unknown packet (" + id + ")");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public int getIdByPacket(Packet packet) {
            try {
                return ServerPackets.keySet().stream().filter((k) -> ServerPackets.get(k).equals(packet.getClass())).limit(1).collect(Collectors.toList()).get(0);
            } catch (IndexOutOfBoundsException e) {
                throw new RuntimeException("Unknown packet (" + packet.getClass().getName() + ")");
            }
        }
    }


    public static class McInputStream extends DataInputStream {

        public McInputStream(InputStream inputStream) {
            super(inputStream);
        }

        public Packet readPacket(Protocol protocol) throws IOException {
            byte[] data = new byte[readVarInt()];
            readFully(data);

            McInputStream input = new McInputStream(new ByteArrayInputStream(data));
            if (protocol.getCompressionTreshold() > 0) {
                int uncompressedlen = input.readVarInt();
                if (uncompressedlen != 0) {
                    byte[] d = new byte[uncompressedlen];
                    int len = input.read(d);

                    Inflater inflater = new Inflater();
                    inflater.setInput(d, 0, len);
                    byte[] result = new byte[uncompressedlen];
                    try {
                        inflater.inflate(result);
                    } catch (Throwable t) {
                        throw new RuntimeException("Invalid compression");
                    }finally {
                        inflater.end();
                    }
                    input = new McInputStream(new ByteArrayInputStream(result));
                }
            }

            int id = input.readUnsignedByte();

            Packet packet = protocol.getPacketById(id);
            try {
                packet.read(input);
            } catch (Throwable t) {
                t.printStackTrace();
                throw t;
            }
            return packet;
        }

        public int readVarInt() throws IOException {
            int numRead = 0;
            int result = 0;
            byte read;
            do {
                read = readByte();
                int value = (read & 0b01111111);
                result |= (value << (7 * numRead));

                numRead++;
                if (numRead > 5) {
                    throw new RuntimeException("VarInt is too big");
                }
            } while ((read & 0b10000000) != 0);

            return result;
        }

    }

    public static class McOutputStream extends DataOutputStream {
        public McOutputStream(OutputStream outputStream) {
            super(outputStream);
        }

        public void writePacket(Packet packet, Protocol protocol) throws IOException {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            output.write(protocol.getIdByPacket(packet));
            packet.write(new McOutputStream(output));

            if (protocol.getCompressionTreshold() > 0) {
                ByteArrayOutputStream data = new ByteArrayOutputStream();
                McOutputStream tmp = new McOutputStream(data);

                if (output.size() > protocol.getCompressionTreshold()) {
                    byte[] result = new byte[output.size()];
                    Deflater deflater = new Deflater();
                    deflater.setInput(data.toByteArray());
                    deflater.finish();
                    int len = deflater.deflate(result);
                    tmp.writeVarInt(len);
                    tmp.write(result, 0, len);
                } else {
                    tmp.writeVarInt(0);
                    tmp.write(output.toByteArray());
                }

                output = new ByteArrayOutputStream();
                output.write(data.toByteArray());
            }

            writeVarInt(output.size());
            write(output.toByteArray());
            flush();
        }

        public void writeVarInt(int value) throws IOException {
            do {
                byte temp = (byte) (value & 0b01111111);
                value >>>= 7;
                if (value != 0) {
                    temp |= 0b10000000;
                }
                writeByte(temp);
            } while (value != 0);
        }
    }

}