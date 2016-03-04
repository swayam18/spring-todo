package com.orangeistehnewblack.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class IsBcryptEncodedFrom extends TypeSafeMatcher<String> {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private String rawString;

    public IsBcryptEncodedFrom(String rawString) {
        this.rawString = rawString;
    }

    @Override
    protected boolean matchesSafely(String encodedString) {
        return encoder.matches(rawString, encodedString);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Bcrypt encoded from: \""+ rawString + "\"");
    }

    @Override
    protected void describeMismatchSafely(String item, Description mismatchDescription) {
        mismatchDescription.appendText("\"" + item + "\" was not encoded from: \"" + rawString + "\"");
    }

    @Factory
    public static TypeSafeMatcher<String> bcryptEncodedFrom(String rawString) { return new IsBcryptEncodedFrom(rawString); }
}
