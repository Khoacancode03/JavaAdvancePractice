package JavaAdvance.models;

public class Student implements Comparable<Student>{
    private int id;
    private String name;
    private Double score;
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Student(int id, String name, Double score, String className) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.className = className;
    }

    public Student() {
    }

    public Student(int id, String name, Double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return this.getScore().compareTo(o.getScore());
    }


}
