package com.example.lab3;

public class NoteClass
{
    private String title;
    private String content;

    public NoteClass()
    {

    }

    public NoteClass(String title, String content)
    {
        this.title = title;
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}
