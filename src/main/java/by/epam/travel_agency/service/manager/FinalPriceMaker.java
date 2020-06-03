package by.epam.travel_agency.service.manager;

import by.epam.travel_agency.bean.Request;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FinalPriceMaker {
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public static void countFinalPriceForSet(Set<Request> set) {
        for (Request req : set) {
            req.setFinalCount(req.getCount().subtract(percentage(req.getCount(), req.getDiscount())));
        }
    }

    public static void countFinalPriceForList(List<Request> list) {
        for (Request req : list) {
            req.setFinalCount(req.getCount().subtract(percentage(req.getCount(), req.getDiscount())));
        }
    }

    public static void deleteCompleteRequest(List<Request> list) {
        Set<Request> temp = new HashSet<>();
        for (Request next : list) {
            for (Request req : list) {
                if (checkCompletedPayment(next, req)) {
                    temp.add(req);
                }
            }
        }
        for (Request req : temp) {
            list.remove(req);
        }
        temp = null;
    }


    private static BigDecimal percentage(BigDecimal base, int pct) {
        return base.multiply(BigDecimal.valueOf(pct)).divide(ONE_HUNDRED, RoundingMode.HALF_UP);
    }

    //return true if 2 request are payed at one tour, by 2 parts
    private static boolean checkCompletedPayment(Request next, Request req) {
        return ((next.getUser().equals(req.getUser()))
                & (next.getTour().getTitle().equals(req.getTour().getTitle()))
                & (next.getPaymentPercentage() + req.getPaymentPercentage() == 100)
                & (next.getId() != (req.getId())));
    }
}
