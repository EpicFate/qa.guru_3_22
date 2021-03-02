package Report;

import io.qameta.allure.restassured.AllureRestAssured;

public class Report {
    private static final AllureRestAssured FILTER = new AllureRestAssured();

    private static class InitLogFilter {
        private static final Report logFilter = new Report();
    }

    private Report() {}

    public static Report filters() {
        return InitLogFilter.logFilter;
    }

    public AllureRestAssured customTemplates() {
        FILTER.setRequestTemplate("request.ftl");
        FILTER.setResponseTemplate("response.ftl");
        return FILTER;
    }
}
