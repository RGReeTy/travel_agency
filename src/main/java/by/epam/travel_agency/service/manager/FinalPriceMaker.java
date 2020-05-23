package by.epam.travel_agency.service.manager;

import by.epam.travel_agency.bean.Request;

import java.math.BigDecimal;
import java.util.Set;

public class FinalPriceMaker {
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public static void countFinalPrice(Set<Request> set) {
        for (Request req : set) {
            req.setFinalCount(req.getCount().subtract(percentage(req.getCount(), req.getDiscount())));
        }
    }


    private static BigDecimal percentage(BigDecimal base, int pct) {
        return base.multiply(BigDecimal.valueOf(pct)).divide(ONE_HUNDRED);
    }
}
