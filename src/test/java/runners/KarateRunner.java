package runners;

import com.intuit.karate.junit5.Karate;

public class KarateRunner {

    @Karate.Test
    Karate testUsersApi() {
        return Karate.run("classpath:features/httpbin-api.feature")
                .relativeTo(getClass());
    }
}