package christmas.global.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("[단위 테스트][Util] CollectionsMapper")
class CollectionsMapperTest {

    @DisplayName("구분자를 기준으로 문자열을 잘라 리스트로 만들 수 있다.")
    @Test
    void splitStringToList() {
        // given
        String input = "타파스-1,제로콜라-1";

        // when
        List<String> actual = CollectionsMapper.splitStringToList(",", input);

        // then
        assertThat(actual).isEqualTo(List.of("타파스-1", "제로콜라-1"));
    }

    @DisplayName("구분자가 기준으로 문자열을 자를 때 값이 없을 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {" , , ,", "타파스-1, ,", "타파스-1,,콜라-1"})
    void throw_exception_when_splitStringToList_has_blank_input(String input) {
        // when & then
        assertThatThrownBy(() -> CollectionsMapper.splitStringToList(",", input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // ",,,"
    @DisplayName("구분자를 기준으로 List의 요소들을 잘라 Map으로 만들 수 있다.")
    @Test
    void listToMap() {
        // given
        List<String> input = List.of("타파스-1", "제로콜라-1");

        // when
        Map<String, Integer> actual = CollectionsMapper.splitListToMap("-", input);

        // then
        assertThat(actual).isEqualTo(Map.of(
                "타파스", 1,
                "제로콜라", 1)
        );
    }

    @DisplayName("구분자를 기준으로 List의 요소들을 잘라 Map으로 만들 때 List가 비어있으면 예외가 발생한다.")
    @Test
    void throw_exception_when_listToMap_has_empty_List() {
        // given
        List<String> input = CollectionsMapper.splitStringToList(",", ",,,");

        // when & then
        assertThatThrownBy(() -> CollectionsMapper.splitListToMap("-", input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
