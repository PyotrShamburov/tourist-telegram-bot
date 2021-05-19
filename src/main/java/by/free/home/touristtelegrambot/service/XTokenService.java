package by.free.home.touristtelegrambot.service;

public interface XTokenService {
    String generateToken();
    String addToRepository(long adminId);
    boolean isAdmin(String token);
}
