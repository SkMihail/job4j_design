package ood.lsp;

public class DeviceOperator {

    private ViolationLSP device;


    public DeviceOperator(ViolationLSP device) {
        this.device = device;
    }

    public void putToCharge(int time) {
        System.out.println(device.charging(time) * 2);
    }

    public static void main(String[] args) {
        DeviceOperator dev = new DeviceOperator(new ChildViolationLSP(100));
        dev.putToCharge(10);

    }
}
