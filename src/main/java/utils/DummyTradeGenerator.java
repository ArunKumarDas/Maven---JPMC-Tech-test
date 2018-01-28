package utils;

import model.trade.Trade;
import model.trade.TradeDetails;
import model.trade.TradeExecute;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;


public class DummyTradeGenerator {
    public static Set<Trade> getDummyTrade() {
        return new HashSet<>(Arrays.asList(

            new Trade(
                "AKD1",
               TradeExecute.BUY,
                LocalDate.of(2018, 1, 10),
                LocalDate.of(2018, 1, 20),
                new TradeDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(0.50),
                        200,
                        BigDecimal.valueOf(100.25))),

            new Trade(
                "AKD2",
               TradeExecute.BUY,
                LocalDate.of(2018, 1, 10),
                LocalDate.of(2018, 1, 19),
                new TradeDetails(
                        Currency.getInstance("AED"),
                        BigDecimal.valueOf(0.22),
                        450,
                        BigDecimal.valueOf(150.5))),

            new Trade(
                "AKD3",
               TradeExecute.SELL,
                LocalDate.of(2018, 1, 10),
                LocalDate.of(2018, 1, 18),
                new TradeDetails(
                        Currency.getInstance("SAR"),
                        BigDecimal.valueOf(0.27),
                        150,
                        BigDecimal.valueOf(400.8))),

            new Trade(
                "AKD4",
               TradeExecute.SELL,
                LocalDate.of(2018, 1, 10),
                LocalDate.of(2018, 1, 21),
                new TradeDetails(
                        Currency.getInstance("EUR"),
                        BigDecimal.valueOf(0.34),
                        50,
                        BigDecimal.valueOf(500.6))),

            new Trade(
                "AKD5",
               TradeExecute.BUY,
                LocalDate.of(2018, 1, 10),
                LocalDate.of(2018, 1, 21),
                new TradeDetails(
                        Currency.getInstance("EUR"),
                        BigDecimal.valueOf(0.34),
                        20,
                        BigDecimal.valueOf(40.6))),

            new Trade(
                "AKD6",
               TradeExecute.BUY,
                LocalDate.of(2018, 1, 10),
                LocalDate.of(2018, 1, 21),
                new TradeDetails(
                        Currency.getInstance("GBP"),
                        BigDecimal.valueOf(0.34),
                        20,
                        BigDecimal.valueOf(40.6))),

            new Trade(
                "AKD7",
               TradeExecute.SELL,
                LocalDate.of(2018, 1, 10),
                LocalDate.of(2018, 1, 21),
                new TradeDetails(
                        Currency.getInstance("EUR"),
                        BigDecimal.valueOf(0.34),
                    1000,
                        BigDecimal.valueOf(160.6))),

          
            new Trade(
                    "AKD8",
                   TradeExecute.SELL,
                    LocalDate.of(2018, 1, 10),
                    LocalDate.of(2018, 1, 21),
                        new TradeDetails(
                                Currency.getInstance("EUR"),
                                BigDecimal.valueOf(0.34),
                            120,
                                BigDecimal.valueOf(500.6))),
            new Trade(
                    "AKD9",
                   TradeExecute.SELL,
                    LocalDate.of(2018, 1, 10),
                    LocalDate.of(2018, 1, 15),
                        new TradeDetails(
                                Currency.getInstance("USD"),
                                BigDecimal.valueOf(0.22),
                            120,
                                BigDecimal.valueOf(500.6))),
            new Trade(
                    "AKD10",
                   TradeExecute.BUY,
                    LocalDate.of(2018, 1, 9),
                    LocalDate.of(2018, 1, 13),
                        new TradeDetails(
                                Currency.getInstance("USD"),
                                BigDecimal.valueOf(0.23),
                            120,
                                BigDecimal.valueOf(200.2))),
            new Trade(
                    "AKD11",
                   TradeExecute.BUY,
                    LocalDate.of(2018, 1, 13),
                    LocalDate.of(2018, 1, 17),
                        new TradeDetails(
                                Currency.getInstance("INR"),
                                BigDecimal.valueOf(0.34),
                            120,
                                BigDecimal.valueOf(500.6))),
            new Trade(
                    "AKD12",
                   TradeExecute.SELL,
                    LocalDate.of(2018, 1, 10),
                    LocalDate.of(2018, 1, 21),
                        new TradeDetails(
                                Currency.getInstance("EUR"),
                                BigDecimal.valueOf(0.34),
                            120,
                                BigDecimal.valueOf(520.3))),
            new Trade(
                    "AKD13",
                   TradeExecute.BUY,
                    LocalDate.of(2018, 1, 10),
                    LocalDate.of(2018, 1, 21),
                        new TradeDetails(
                                Currency.getInstance("USD"),
                                BigDecimal.valueOf(0.34),
                            120,
                                BigDecimal.valueOf(300.2))),
            new Trade(
                    "AKD14",
                   TradeExecute.SELL,
                    LocalDate.of(2018, 1, 10),
                    LocalDate.of(2018, 1, 21),
                        new TradeDetails(
                                Currency.getInstance("EUR"),
                                BigDecimal.valueOf(0.34),
                            120,
                                BigDecimal.valueOf(120.7)))
        ));
    }
}