package com.example.rest_api_with_jwt;

import com.example.rest_api_with_jwt.util.Util;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class UtilTest {
    @Test
    @DisplayName("Util.mapOf")
    void t1() {
        Map<String, Integer> ages = Util.mapOf("영수", 22, "철수", 33, "영희", 44, "민수", 55);

        assertThat(ages.get("영수")).isEqualTo(22);
        assertThat(ages.get("철수")).isEqualTo(33);
        assertThat(ages.get("영희")).isEqualTo(44);
        assertThat(ages.get("민수")).isEqualTo(55);
    }
}