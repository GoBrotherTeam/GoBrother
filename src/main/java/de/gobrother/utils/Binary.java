package de.gobrother.utils;

import java.io.DataOutputStream;
import java.io.IOException;

public class Binary {

    public static int readVarInt(byte read1) {
        int numRead = 0;
        int result = 0;
        byte read;
        do {
            read = read1;
            int value = (read & 0b01111111);
            result |= (value << (7 * numRead));

            numRead++;
            if (numRead > 5) {
                throw new RuntimeException("VarInt is too big");
            }
        } while ((read & 0b10000000) != 0);

        return result;
    }

    public static void writeVarInt(DataOutputStream outputStream, int value) {
        do {
            byte temp = (byte)(value & 0b01111111);
            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value >>>= 7;
            if (value != 0) {
                temp |= 0b10000000;
            }

            try {
                outputStream.writeByte(temp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (value != 0);
    }

    public static String writeVarLong(long value) {
        String encoded = "";
        do {
            byte temp = (byte)(value & 0b01111111);
            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value >>>= 7;
            if (value != 0) {
                temp |= 0b10000000;
            }

            encoded += temp;
        } while (value != 0);

        return encoded;
    }

}
