package de.gobrother.utils;

public class TextFormat {

    public String toJson(String message) {
        String[] splittedMessages = message.replace("§", "%!COLOR!%").replace("Â", "").split("%!COL");
        String newMessage = "";

        if(splittedMessages.length != 1) {
            newMessage += "[";
        }

        String string1 = "";

        String color = "white";
        boolean bold = false;
        boolean italic = false;
        boolean underlined = false;
        boolean strikethrough = false;
        boolean obfuscated = false;

        int i = 1;
        for(String partMessage1 : splittedMessages) {
            String partMessage = partMessage1.replace("OR!%", "§");

            if(partMessage.contains("§")) {
                string1 = colorToString(partMessage.substring(1, 2));
                if(string1.contains("!")) {
                    if(string1.contains("bold")) {
                        bold = true;
                    } else if(string1.contains("obfuscated")) {
                        obfuscated = true;
                    } else if(string1.contains("italic")) {
                        italic = true;
                    } else if(string1.contains("underlined")) {
                        underlined = true;
                    } else if(string1.contains("strikethrough")) {
                        strikethrough = true;
                    } else if(string1.contains("reset")) {
                        color = "white";

                        bold = false;
                        obfuscated = false;
                        italic = false;
                        underlined = false;
                        strikethrough = false;
                    }
                } else {
                    color = string1;
                }
            }
            if(partMessage.contains("§")) {
                if(!partMessage.substring(2, partMessage.length()).equals("")) {
                    newMessage +=
                            "{\"text\":\"" + partMessage.substring(2, partMessage.length()) +
                                    "\",\"color\":\"" + color + "\"," +
                                    "\"bold\":" + bold + "," +
                                    "\"obfuscated\":" + obfuscated + "," +
                                    "\"italic\":" + italic + "," +
                                    "\"underlined\":" + underlined + "," +
                                    "\"strikethrough\":" + strikethrough + "},";
                }
            } else {
                if(!partMessage.equals("")) {
                    newMessage +=
                            "{\"text\":\"" + partMessage +
                                    "\",\"color\":\"" + color + "\"," +
                                    "\"bold\":" + bold + "," +
                                    "\"obfuscated\":" + obfuscated + "," +
                                    "\"italic\":" + italic + "," +
                                    "\"underlined\":" + underlined + "," +
                                    "\"strikethrough\":" + strikethrough + "},";
                }
            }

            i++;
        }

        if(newMessage.substring(newMessage.length() - 1, newMessage.length()).equals(",")) {
            newMessage = newMessage.substring(0, newMessage.length() - 1);
        }

        if(splittedMessages.length != 1) {
            newMessage += "]";
        }

        return newMessage;
    }

    private String colorToString(String colorCode) {
        if(colorCode.equals("0")) {
            return "black";
        } else if(colorCode.equals("1")) {
            return "dark_blue";
        } else if(colorCode.equals("2")) {
            return "dark_green";
        } else if(colorCode.equals("3")) {
            return "dark_aqua";
        } else if(colorCode.equals("4")) {
            return "dark_red";
        } else if(colorCode.equals("5")) {
            return "dark_purple";
        } else if(colorCode.equals("6")) {
            return "gold";
        } else if(colorCode.equals("7")) {
            return "gray";
        } else if(colorCode.equals("8")) {
            return "dark_gray";
        } else if(colorCode.equals("9")) {
            return "blue";
        } else if(colorCode.equals("a")) {
            return "green";
        } else if(colorCode.equals("b")) {
            return "aqua";
        } else if(colorCode.equals("c")) {
            return "red";
        } else if(colorCode.equals("d")) {
            return "light_purple";
        } else if(colorCode.equals("e")) {
            return "yellow";
        } else if(colorCode.equals("f")) {
            return "white";
        }

        else if(colorCode.equals("k")) {
            return "!obfuscated";
        } else if(colorCode.equals("l")) {
            return "!bold";
        } else if(colorCode.equals("m")) {
            return "!strikethrough";
        } else if(colorCode.equals("n")) {
            return "!underlined";
        } else if(colorCode.equals("o")) {
            return "!italic";
        } else if(colorCode.equals("r")) {
            return "!reset";
        }

        return "not_found";
    }

}
