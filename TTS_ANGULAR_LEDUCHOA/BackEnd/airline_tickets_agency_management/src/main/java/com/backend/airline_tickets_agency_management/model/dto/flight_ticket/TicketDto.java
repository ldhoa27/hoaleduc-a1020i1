package com.backend.airline_tickets_agency_management.model.dto.flight_ticket;

import com.backend.airline_tickets_agency_management.model.entity.customer.Customer;
import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.Flight;
import com.backend.airline_tickets_agency_management.model.entity.flight_ticket.TicketStatus;

import com.backend.airline_tickets_agency_management.model.entity.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TicketDto {
    private Long ticketId;
    private String ticketCode;
    private Boolean plusBaby;
    @NumberFormat(pattern = "^[1-9]\\d*$")
    private Double plusBaggage;
    private String ticketType;
    private Double ticketTypePrice;
    private Double ticketPrice;
    private Double tax;
    private String chairName;
    private String bookingDate;
    private Integer checkInBaggage;
    private Integer carryOnBaggage;
    private String passengerType;
    private Double passengerTypePrice;
    @NotBlank
    @Size(max = 50, min = 10)
    @Pattern(regexp = "[A-ZẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴa-zàáâãèéêìíòóôõùúăđĩũơưăạảấầẩẫậắằẳẵặẹẻẽềềểễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹ ]*")

    private String passengerName;
    @NotBlank
    private String passengerGender;
    @Pattern(regexp = "^\\(\\+84\\)+90[0-9]{7}|\\(\\+84\\)+91[0-9]{7}$")
    private String passengerPhone;
    @NotBlank
    @Pattern(regexp = "^[0-9]{9,10}$")
    private String passengerIdCard;
    @Email
    @Pattern(regexp = "[A-Za-z0-9._%-]+@[A-Za-z0-9._%-]+\\.[a-z]{2,3}")
    private String passengerEmail;
    private Flight flight;
    private TicketStatus ticketStatus;

    private User user;

    public TicketDto() {
    }

    public TicketDto(Long ticketId, String ticketCode, Boolean plusBaby, Double plusBaggage, String ticketType, Double ticketTypePrice, Double ticketPrice, Double tax, String chairName, String bookingDate, Integer checkInBaggage, Integer carryOnBaggage, String passengerType, Double passengerTypePrice, String passengerName, String passengerGender,  String passengerPhone, String passengerIdCard,  String passengerEmail, Flight flight, TicketStatus ticketStatus, User user) {
        this.ticketId = ticketId;
        this.ticketCode = ticketCode;
        this.plusBaby = plusBaby;
        this.plusBaggage = plusBaggage;
        this.ticketType = ticketType;
        this.ticketTypePrice = ticketTypePrice;
        this.ticketPrice = ticketPrice;
        this.tax = tax;
        this.chairName = chairName;
        this.bookingDate = bookingDate;
        this.checkInBaggage = checkInBaggage;
        this.carryOnBaggage = carryOnBaggage;
        this.passengerType = passengerType;
        this.passengerTypePrice = passengerTypePrice;
        this.passengerName = passengerName;
        this.passengerGender = passengerGender;
        this.passengerPhone = passengerPhone;
        this.passengerIdCard = passengerIdCard;
        this.passengerEmail = passengerEmail;
        this.flight = flight;
        this.ticketStatus = ticketStatus;
        this.user = user;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public Boolean getPlusBaby() {
        return plusBaby;
    }

    public void setPlusBaby(Boolean plusBaby) {
        this.plusBaby = plusBaby;
    }

    public Double getPlusBaggage() {
        return plusBaggage;
    }

    public void setPlusBaggage(Double plusBaggage) {
        this.plusBaggage = plusBaggage;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public Double getTicketTypePrice() {
        return ticketTypePrice;
    }

    public void setTicketTypePrice(Double ticketTypePrice) {
        this.ticketTypePrice = ticketTypePrice;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public String getChairName() {
        return chairName;
    }

    public void setChairName(String chairName) {
        this.chairName = chairName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Integer getCheckInBaggage() {
        return checkInBaggage;
    }

    public void setCheckInBaggage(Integer checkInBaggage) {
        this.checkInBaggage = checkInBaggage;
    }

    public Integer getCarryOnBaggage() {
        return carryOnBaggage;
    }

    public void setCarryOnBaggage(Integer carryOnBaggage) {
        this.carryOnBaggage = carryOnBaggage;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    public Double getPassengerTypePrice() {
        return passengerTypePrice;
    }

    public void setPassengerTypePrice(Double passengerTypePrice) {
        this.passengerTypePrice = passengerTypePrice;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerGender() {
        return passengerGender;
    }

    public void setPassengerGender(String passengerGender) {
        this.passengerGender = passengerGender;
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public String getPassengerIdCard() {
        return passengerIdCard;
    }

    public void setPassengerIdCard(String passengerIdCard) {
        this.passengerIdCard = passengerIdCard;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
