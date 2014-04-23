package net.pannala.converter;

public class CentigradeFarenhietConverter implements TwoWayConverterInterface {

    @Override
    public float to(float centigrade) {
	return 32.0f + 9 * centigrade / 5;
    }

    @Override
    public float from(float farenhiet) {
	return (farenhiet - 32) * 5 / 9;
    }
}
