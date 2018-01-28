package business_logic.stockreports;


import business_logic.StockSettledDate;
import business_logic.StockSettleStatus;
import business_logic.ranking.Rank;
import model.trade.Trade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class StockReportGen implements IStockReportGen  {
    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public String generateStockReportGen(Set<Trade> trade) {
        // first calculate the correct settlement dates
        StockSettledDate.calculateSettlementDates(trade);

        // Build the report string
        return generateDailyOutgoingRanking(trade,
                generateDailyIncomingRanking(trade,
                generateDailyIncomingAmount(trade,
                generateDailyOutgoingAmount(trade, stringBuilder))))
            .toString();
    }

    private static StringBuilder generateDailyOutgoingAmount(Set<Trade> instructions, StringBuilder stringBuilder) {
        final Map<LocalDate, BigDecimal> dailyOutgoingAmount =
                StockSettleStatus.calculateDailyOutgoingAmount(instructions);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("       ::: Daily Outgoing Amount  :::       \n")
                .append("----------------------------------------\n")
                .append("      Date       |    Trade Amount      \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingAmount.keySet()) {
            stringBuilder
                .append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
        }

        return stringBuilder;
    }

    private static StringBuilder generateDailyIncomingAmount(Set<Trade> trade, StringBuilder stringBuilder) {
        final Map<LocalDate, BigDecimal> dailyOutgoingAmount =
        		StockSettleStatus.calculateDailyIncomingAmount(trade);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("          ::: Daily Incoming Amount :::         \n")
                .append("----------------------------------------\n")
                .append("      Date       |    Trade Amount      \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingAmount.keySet()) {
            stringBuilder
                    .append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
        }

        return stringBuilder;
    }

    private static StringBuilder generateDailyOutgoingRanking(Set<Trade> trade, StringBuilder stringBuilder) {
        final Map<LocalDate, LinkedList<Rank>> dailyOutgoingRanking =
        		StockSettleStatus.calculateDailyOutgoingRanking(trade);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Outgoing Daily Ranking          \n")
                .append("----------------------------------------\n")
                .append("     Date    |     Rank   |   Entity     \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingRanking.keySet()) {
            for (Rank rank : dailyOutgoingRanking.get(date)) {
                stringBuilder
                    .append(date).append("   |      ")
                    .append(rank.getRank()).append("     |    ")
                    .append(rank.getEntity()).append("\n");
            }
        }

        return stringBuilder;
    }

    private static StringBuilder generateDailyIncomingRanking(Set<Trade> trade, StringBuilder stringBuilder) {
        final Map<LocalDate, LinkedList<Rank>> dailyIncomingRanking =
        		StockSettleStatus.calculateDailyIncomingRanking(trade);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Incoming Daily Ranking          \n")
                .append("----------------------------------------\n")
                .append("     Date    |     Rank   |   Entity     \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyIncomingRanking.keySet()) {
            for (Rank rank : dailyIncomingRanking.get(date)) {
                stringBuilder
                        .append(date).append("   |      ")
                        .append(rank.getRank()).append("     |    ")
                        .append(rank.getEntity()).append("\n");
            }
        }

        return stringBuilder;
    }

	


	

}
