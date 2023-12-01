package blackjack.controller.dto;

public class PlayerResultDto {
    private final String name;
    private final double bettingMoney;

    public PlayerResultDto(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public String getName() {
        return name;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }
}
