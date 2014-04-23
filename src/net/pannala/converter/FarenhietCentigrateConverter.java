package net.pannala.converter;

public class FarenhietCentigrateConverter implements TwoWayConverterInterface {

    @Override
    public float to(float farenhiet) {
	return (farenhiet - 32) * 5 / 9;
    }

    @Override
    public float from(float centigrade) {
	return 32.0f + 9 * centigrade / 5;
    }
}
