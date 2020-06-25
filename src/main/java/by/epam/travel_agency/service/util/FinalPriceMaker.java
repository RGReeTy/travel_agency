package by.epam.travel_agency.service.util;

import by.epam.travel_agency.bean.Defrayal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FinalPriceMaker {
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public static void countFinalPriceForSet(Set<Defrayal> set) {
        for (Defrayal req : set) {
            req.setFinalCount(req.getCount().subtract(countNumeralValueOfDiscount(req.getCount(), req.getDiscount())));
        }
    }

    public static void countFinalPriceForList(List<Defrayal> list) {
        for (Defrayal req : list) {
            req.setFinalCount(req.getCount().subtract(countNumeralValueOfDiscount(req.getCount(), req.getDiscount())));
        }
    }

    public static void deleteCompleteRequest(List<Defrayal> list) {
        Set<Defrayal> temp = new HashSet<>();
        for (Defrayal next : list) {
            for (Defrayal req : list) {
                if (checkCompletedPayment(next, req)) {
                    temp.add(req);
                }
            }
        }
        for (Defrayal req : temp) {
            list.remove(req);
        }
        temp = null;
    }


    public static BigDecimal countNumeralValueOfDiscount(BigDecimal base, int pct) {
        return base.multiply(BigDecimal.valueOf(pct)).divide(ONE_HUNDRED, RoundingMode.HALF_UP);
    }

    //return true if 2 request are payed at one tour, by 2 parts
    private static boolean checkCompletedPayment(Defrayal next, Defrayal req) {
        return ((next.getUser().equals(req.getUser()))
                & (next.getTour().getTitle().equals(req.getTour().getTitle()))
                & (next.getPaymentPercentage() + req.getPaymentPercentage() == 100)
                & (next.getId() != (req.getId())));
    }
}
