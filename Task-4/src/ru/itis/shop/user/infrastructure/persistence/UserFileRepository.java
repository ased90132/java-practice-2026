//package ru.itis.shop.user.infrastructure.persistence;
//
//import ru.itis.shop.user.domain.User;
//import ru.itis.shop.user.repository.UserRepository;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//public class UserFileRepository implements UserRepository {
//
//    private final String fileName;
//
//    private final UserMapper userMapper;
//
//    public UserFileRepository(String fileName, UserMapper userMapper) {
//        this.fileName = fileName;
//        this.userMapper = userMapper;
//    }
//
//    @Override
//    public void save(User user) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
//            String id = UUID.randomUUID().toString();
//            user.setId(id);
//            writer.write(userMapper.toLine(user));
//            writer.newLine();
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//    }
//
//    @Override
//    public Optional<User> findByEmail(String email) {
//        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
//
//            String line = reader.readLine();
//
//            while (line != null) {
//
//                User user = userMapper.fromLine(line);
//
//                if (user.getEmail().equals(email)) {
//                    return Optional.of(user);
//                }
//
//                line = reader.readLine();
//            }
//
//            return Optional.empty();
//
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//    }
//
//    @Override
//    public Optional<User> findById(String id) {
//        if (id == null) {
//            return Optional.empty();
//        }
//        File file = new File(fileName);
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split("\\|", 4);
//                if (parts.length == 4 && parts[0].equals(id)) {
//                    User user = new User(parts[1], parts[2], parts[3]);
//                    user.setId(parts[0]);
//                    return Optional.of(user);
//                }
//            }
//        }catch (IOException e){
//            throw new IllegalStateException(e);
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public void updateDescription(String email, String description) {
//        File file = new File(fileName);
//        Optional<User> userOptional = findByEmail(email);
//        if (userOptional.isEmpty()) {
//            throw new IllegalArgumentException("Пользователя с почтой " + email + " не существует");
//        }
//        List<String> lines = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                lines.add(line);
//            }
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//        boolean updated = false;
//        for (int i = 0; i < lines.size(); i++) {
//            String[] parts = lines.get(i).split("\\|");
//            if (parts.length == 4 && parts[1].equals(email)) {
//                String newLine = parts[0] + "|" + parts[1] + "|" + parts[2] + "|" + description;
//                lines.set(i, newLine);
//                updated = true;
//                break;
//            }
//        }
//        if (!updated) {
//            throw new IllegalStateException("не получилось поменять описание");
//        }
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//            for (String line : lines) {
//                writer.write(line);
//                writer.newLine();
//            }
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//
//    }
//}
