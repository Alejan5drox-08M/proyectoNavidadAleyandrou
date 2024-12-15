package application.Utils;

import java.time.LocalDate;

public class ParsearFecha {
    public static String fechaGetMonth(LocalDate fecha) {// solo el mes
        String mes = "";
        return switch (fecha.getMonthValue()) {
            case 1 -> "enero";
            case 2 -> "febrero";
            case 3 -> "marzo";
            case 4 -> "abril";
            case 5 -> "mayo";
            case 6 -> "junio";
            case 7 -> "julio";
            case 8 -> "agosto";
            case 9 -> "septiembre";
            case 10 -> "octubre";
            case 11 -> "noviembre";
            case 12 -> "diciembre";
            default -> "error";
        };
    }
}