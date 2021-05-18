package by.free.home.touristtelegrambot.service;

public interface XTokenService {
    public String generateToken();
    public String addToRepository(long adminId);
    public boolean isAdmin(String token);
    public boolean validToken(String token);
}
