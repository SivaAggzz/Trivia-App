package com.techneapps.triviaapp.database.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "quiz_history")
public class QuizHistory implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "quiz_id")
    public long id;
    @ColumnInfo(name = "quiz_timeStamp")
    public long timeStamp;
    @ColumnInfo(name = "quiz_userName")
    public String userName;
    @ColumnInfo(name = "quiz_quizQuestion1")
    public String quizQuestion1;
    @ColumnInfo(name = "quiz_quizQuestion2")
    public String quizQuestion2;
    @ColumnInfo(name = "quiz_quizAnswered1")
    public String quizAnswered1;
    @ColumnInfo(name = "quiz_quizAnswered2")
    public String quizAnswered2;

    @Ignore()
    public QuizHistory() {
    }

    public QuizHistory(long timeStamp, String userName, String quizQuestion1, String quizQuestion2, String quizAnswered1, String quizAnswered2) {
        this.timeStamp = timeStamp;
        this.userName = userName;
        this.quizQuestion1 = quizQuestion1;
        this.quizQuestion2 = quizQuestion2;
        this.quizAnswered1 = quizAnswered1;
        this.quizAnswered2 = quizAnswered2;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQuizQuestion1() {
        return quizQuestion1;
    }

    public void setQuizQuestion1(String quizQuestion1) {
        this.quizQuestion1 = quizQuestion1;
    }

    public String getQuizQuestion2() {
        return quizQuestion2;
    }

    public void setQuizQuestion2(String quizQuestion2) {
        this.quizQuestion2 = quizQuestion2;
    }

    public String getQuizAnswered1() {
        return quizAnswered1;
    }

    public void setQuizAnswered1(String quizAnswered1) {
        this.quizAnswered1 = quizAnswered1;
    }

    public String getQuizAnswered2() {
        return quizAnswered2;
    }

    public void setQuizAnswered2(String quizAnswered2) {
        this.quizAnswered2 = quizAnswered2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.timeStamp);
        dest.writeString(this.userName);
        dest.writeString(this.quizQuestion1);
        dest.writeString(this.quizQuestion2);
        dest.writeString(this.quizAnswered1);
        dest.writeString(this.quizAnswered2);
    }

    protected QuizHistory(Parcel in) {
        this.id = in.readLong();
        this.timeStamp = in.readLong();
        this.userName = in.readString();
        this.quizQuestion1 = in.readString();
        this.quizQuestion2 = in.readString();
        this.quizAnswered1 = in.readString();
        this.quizAnswered2 = in.readString();
    }



    public static final Parcelable.Creator<QuizHistory> CREATOR = new Parcelable.Creator<QuizHistory>() {
        @Override
        public QuizHistory createFromParcel(Parcel source) {
            return new QuizHistory(source);
        }

        @Override
        public QuizHistory[] newArray(int size) {
            return new QuizHistory[size];
        }
    };
}
