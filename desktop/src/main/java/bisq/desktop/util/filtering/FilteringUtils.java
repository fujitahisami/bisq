package bisq.desktop.util.filtering;

import bisq.core.trade.model.bisq_v1.Contract;

public class FilteringUtils {
    public static boolean match(Contract contract, String filterString) {
        boolean isBuyerOnion = false;
        boolean isSellerOnion = false;
        boolean matchesBuyersPaymentAccountData = false;
        boolean matchesSellersPaymentAccountData = false;
        if (contract != null) {
            isBuyerOnion = contract.getBuyerNodeAddress().getFullAddress().contains(filterString);
            isSellerOnion = contract.getSellerNodeAddress().getFullAddress().contains(filterString);
            matchesBuyersPaymentAccountData = contract.getBuyerPaymentAccountPayload() != null &&
                    contract.getBuyerPaymentAccountPayload().getPaymentDetails().contains(filterString);
            matchesSellersPaymentAccountData = contract.getSellerPaymentAccountPayload() != null &&
                    contract.getSellerPaymentAccountPayload().getPaymentDetails().contains(filterString);
        }
        return isBuyerOnion || isSellerOnion ||
                matchesBuyersPaymentAccountData || matchesSellersPaymentAccountData;
    }
}
