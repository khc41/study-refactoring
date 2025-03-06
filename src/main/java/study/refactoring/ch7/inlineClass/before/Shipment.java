package study.refactoring.ch7.inlineClass.before;

public class Shipment {
    private TrackingInformation trackingInformation;

    public String getTrackingInfo() {
        return trackingInformation.getDisplay();
    }

    public TrackingInformation getTrackingInformation() {
        return trackingInformation;
    }

    public void setTrackingInformation(TrackingInformation trackingInformation) {
        this.trackingInformation = trackingInformation;
    }
}
