import java.io.*;
import java.util.*;
public class Main {
    public static class Student implements Comparable<Student>{
        String name;
        int day;
        int month;
        int year;
        public Student(String name, int day, int month, int year){
            this.name = name;
            this.day = day;
            this.month = month;
            this.year = year;
        }
        @Override
        public int compareTo(Student o){
            if(this.year != o.year){
                return Integer.compare(this.year, o.year);
            }else{
                if(this.month != o.month){
                    return Integer.compare(this.month, o.month);
                }else{
                    return Integer.compare(this.day, o.day);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Student[] students = new Student[N];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            students[i] = new Student(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(students);

        StringBuilder sb = new StringBuilder();
        sb.append(students[N-1].name).append("\n").append(students[0].name);
        System.out.println(sb);
    }
}
