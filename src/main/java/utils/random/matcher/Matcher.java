package utils.random.matcher;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;

public class Matcher {
    public static <T> void assertThat(String reason, T actual, org.hamcrest.Matcher<? super T> matcher) {
        if (!matcher.matches(actual)) {
            Description description = new StringDescription();
            description.appendText(reason).appendText(" Expected: ").appendDescriptionOf(matcher).appendText(" but: ");
            matcher.describeMismatch(actual, description);
            throw new AssertionError(description.toString());
        }
    }

    public static <T> void assertThat(T actual, org.hamcrest.Matcher<? super T> matcher) {
        assertThat("", actual, matcher);
    }
}
