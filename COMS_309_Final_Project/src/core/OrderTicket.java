package core;

public class OrderTicket {
    private String dishName;
    private String specialRequest;

    public OrderTicket(String dishName, String specialRequest) {
        this.dishName = dishName;
        this.specialRequest = specialRequest;
    }

    public String getDishName() {
        return dishName;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    @Override
    public String toString() {
        return "Dish: " + dishName + (specialRequest.isEmpty() ? "" : " (Special Request: " + specialRequest + ")");
    }
}
