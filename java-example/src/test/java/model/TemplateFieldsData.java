package model;

public class TemplateFieldsData {
    private String surveyName;
    private Integer type;
    private boolean withSelfRating;
    private Integer publish;
    private String description;
    private String startDate;
    private String endDate;
    private Integer period;
    private char periodType;
    private Integer startAnswer;
    private Integer endAnswer;
    private Integer datePublish;
    private Integer questionType;
    private String questionText;

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public boolean isWithSelfRating() {
        return withSelfRating;
    }

    public void setWithSelfRating(boolean withSelfRating) {
        this.withSelfRating = withSelfRating;
    }

    public Integer getPublish() {
        return publish;
    }

    public void setPublish(Integer publish) {
        this.publish = publish;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public char getPeriodType() {
        return periodType;
    }

    public void setPeriodType(char periodType) {
        this.periodType = periodType;
    }

    public Integer getStartAnswer() {
        return startAnswer;
    }

    public void setStartAnswer(Integer startAnswer) {
        this.startAnswer = startAnswer;
    }

    public Integer getEndAnswer() {
        return endAnswer;
    }

    public void setEndAnswer(Integer endAnswer) {
        this.endAnswer = endAnswer;
    }

    public Integer getDatePublish() {
        return datePublish;
    }

    public void setDatePublish(Integer datePublish) {
        this.datePublish = datePublish;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
