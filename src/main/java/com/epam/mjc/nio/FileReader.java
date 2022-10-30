package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.InputMismatchException;


public class FileReader {
    public Profile getDataFromFile(File file) {
        StringBuilder name = new StringBuilder();
        StringBuilder age = new StringBuilder();
        StringBuilder email = new StringBuilder();
        StringBuilder phone = new StringBuilder();
        try (BufferedReader newBufferedReader = Files.newBufferedReader(file.toPath())) {
            int c;
            boolean keyEnd = false;
            StringBuilder key = new StringBuilder();
            while ((c = newBufferedReader.read()) != -1) {
                char ch = (char) c;

                switch (ch) {
                    case ':':
                        keyEnd = true;
                        break;
                    case ' ':
                        continue;
                    case '\n':
                        keyEnd = false;
                        key = new StringBuilder();
                        break;
                    default:
                        break;
                }


                if (!keyEnd) {
                    key.append(ch);
                } else {
                    switch (key.toString().strip()) {
                        case "Name":
                            name.append(ch);
                            break;
                        case "Age":
                            age.append(ch);
                            break;
                        case "Email":
                            email.append(ch);
                            break;
                        case "Phone":
                            phone.append(ch);
                            break;
                        default:
                            throw new InputMismatchException("Key '" + key + "' is not supported.");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Profile(name.toString(), Integer.parseInt(age.toString()), email.toString(), Long.parseLong(phone.toString()));
    }
}