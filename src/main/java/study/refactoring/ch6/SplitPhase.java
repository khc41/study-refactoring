package study.refactoring.ch6;

public class SplitPhase {

    public static int priceOrder(Product product, int quantity, ShippingMethod shippingMethod) {
        final int basePrice = product.getBasePrice() * quantity;
        final int discount = (int) (Math.max(quantity - product.getDiscountThreshold(), 0)
                * product.getBasePrice() * product.getDiscountRate());
        final PriceData priceData = new PriceData(basePrice, quantity, discount);
        final int price = applyShipping(priceData, shippingMethod);
        return price;
    }

    public static int applyShipping(PriceData priceData, ShippingMethod shippingMethod) {
        final int shippingPerCase = (priceData.getBasePrice() > shippingMethod.getDiscountThreshold())
                ? shippingMethod.getDiscountedFee() : shippingMethod.getFeePerCase();
        final int shippingCost = priceData.getQuantity() * shippingPerCase;
        final int price = priceData.getBasePrice() - priceData.getDiscount() + shippingCost;
        return price;
    }

    public static class PriceData {
       private final int basePrice;
       private final int quantity;
       private final int discount;

        public PriceData(int basePrice, int quantity, int discount) {
            this.basePrice = basePrice;
            this.quantity = quantity;
            this.discount = discount;
        }

        public int getDiscount() {
            return discount;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getBasePrice() {
            return basePrice;
        }
    }

    public static class Product {
        private int basePrice;
        private int discountThreshold;
        private double discountRate;

        public Product(int basePrice, int discountThreshold, double discountRate) {
            this.basePrice = basePrice;
            this.discountThreshold = discountThreshold;
            this.discountRate = discountRate;
        }

        public int getBasePrice() {
            return basePrice;
        }

        public int getDiscountThreshold() {
            return discountThreshold;
        }

        public double getDiscountRate() {
            return discountRate;
        }
    }

    public static class ShippingMethod {
        private int discountedFee;
        private int feePerCase;
        private int discountThreshold;

        public ShippingMethod(int discountedFee, int feePerCase, int discountThreshold) {
            this.discountedFee = discountedFee;
            this.feePerCase = feePerCase;
            this.discountThreshold = discountThreshold;
        }

        public int getDiscountedFee() {
            return discountedFee;
        }

        public int getFeePerCase() {
            return feePerCase;
        }

        public int getDiscountThreshold() {
            return discountThreshold;
        }
    }
}
