package com.boa.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.boa.api.web.rest.TestUtil;

public class RequestParamTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequestParam.class);
        RequestParam requestParam1 = new RequestParam();
        requestParam1.setId(1L);
        RequestParam requestParam2 = new RequestParam();
        requestParam2.setId(requestParam1.getId());
        assertThat(requestParam1).isEqualTo(requestParam2);
        requestParam2.setId(2L);
        assertThat(requestParam1).isNotEqualTo(requestParam2);
        requestParam1.setId(null);
        assertThat(requestParam1).isNotEqualTo(requestParam2);
    }
}
