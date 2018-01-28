package model.trade;

import model.trade.TradeDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

/**
 * Describes an instruction sent by various clients in order to buy or sell
 */
public class Trade {

    // A financial entity whose shares are to be bought or sold
    private final String entity;

    // What action should the Instruction represents (Buy or Sell)
    private final TradeExecute execute;

    // LocalDate on which the instruction was sent by various clients
    private final LocalDate instructionDate;

    // The Date on which the client wished for the instruction to be settled with respect to Instruction LocalDate
    // The only non-final since it can be recalculated from business logic.
    private LocalDate settlementDate;

    private final TradeDetails details;

    public Trade(
            String entity,
            TradeExecute execute,
            LocalDate instructionDate,
            LocalDate settlementDate,
            TradeDetails details)
    {
        this.entity = entity;
        this.execute = execute;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.details = details;
    }

    public String getEntity() {
        return entity;
    }

    public TradeExecute getAction() {
        return execute;
    }

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public void setSettlementDate(LocalDate newDate) {
        settlementDate = newDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public TradeDetails getDetails() {
        return details;
    }

    public Currency getCurrency() {
        return getDetails().getCurrency();
    }

    public BigDecimal getAgreedFx() {
        return getDetails().getAgreedFx();
    }

    public int getUnits() {
        return getDetails().getUnits();
    }

    public BigDecimal getPricePerUnit() {
        return getDetails().getPricePerUnit();
    }

    public BigDecimal getTradeAmount() {
        return getDetails().getTradeAmount()
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    @Override
    public String toString() {
        return entity;
    }
}

