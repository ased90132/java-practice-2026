package ru.itis.shop.user.infrastructure.persistence;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.io.*;
import java.util.UUID;

public class UserFileRepository implements UserRepository {

    private final String fileName;

    public UserFileRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String id = UUID.randomUUID().toString();
            user.setId(id);
            writer.write(user.getId() + "|" +
                    user.getEmail() + "|" +
                    user.getPassword() + "|" +
                    user.getProfileDescription());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public User findById(String id) {
        if (id == null) {
            return null;
        }
        File file = new File(fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;

            while((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|",-1);
                if (parts.length >= 4 && parts[0].equals(id)) {
                    StringBuilder description = new StringBuilder(parts[3]);
                    for (int i = 4; i < parts.length; i++) {
                        description.append("|").append(parts[i]);
                    }
                    User user = new User(parts[1], parts[2], description.toString());
                    user.setId(parts[0]);
                    return user;
                }
            }
        }catch (IOException e){
            throw new IllegalStateException(e);
        }
        return null;
    }
}
