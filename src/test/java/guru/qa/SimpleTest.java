package guru.qa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.*;

public class SimpleTest {

    @BeforeEach
    void openMtsSite() {
        open("https://moskva.mts.ru/personal");
        $x("//span[contains(text(), 'Для дома')]").click();
        $x("//div[contains(text(), 'Домашний интернет, ТВ')]").click();
    }

    static Stream<Arguments> checkTabsNamesOnHomeInternetTvPhonePage() {
        return Stream.of(
                Arguments.of(List.of("Для всех", "Интернет + ТВ", "Интернет", "ТВ", "Спутниковое ТВ"))
        );
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("Проверка коррекности названий вкладок на странице Домашний интернет, ТВ и телефон - {0}")
    void checkTabsNamesOnHomeInternetTvPhonePage(List<String> expectedText) {
        $$(".home-services-tab").shouldHave(texts(expectedText));
    }
}
