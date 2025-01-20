package com.testdroid.api.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;

/**
 * @author Michał Szpruta <michal.szpruta@smartbear.com>
 */
public class RandomUtils {

    private RandomUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static final UniformRandomProvider RNG = RandomSource.XO_RO_SHI_RO_128_PP.create();

    public static final RandomStringUtils RSU = RandomStringUtils.secure();

}
