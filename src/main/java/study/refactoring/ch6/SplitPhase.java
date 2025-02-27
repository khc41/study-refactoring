package study.refactoring.ch6;

public class SplitPhase {

    public static int priceOrder(Product product, int quantity, ShippingMethod shippingMethod) {
        final int basePrice = product.getBasePrice() * quantity;
        final int discount = (int) (Math.max(quantity - product.getDiscountThreshold(), 0)
                * product.getBasePrice() * product.getDiscountRate());

        final int price = applyShipping(basePrice, shippingMethod, quantity, discount);
        return price;
    }

    public static int applyShipping(int basePrice, ShippingMethod shippingMethod, int quantity, int discount) {
        final int shippingPerCase = (basePrice > shippingMethod.getDiscountThreshold())
                ? shippingMethod.getDiscountedFee() : shippingMethod.getFeePerCase();
        final int shippingCost = quantity * shippingPerCase;
        final int price = basePrice - discount + shippingCost;
        return price;
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
