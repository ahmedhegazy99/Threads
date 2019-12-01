
package mergesort;
import java.util.Random;

public class MergeSort {
    
    public static Random rand = new Random();
    
    public static void merge( Student []a ,Student[] l , Student[] r) {
        int i = 0, j = 0, k = 0;
        while (i < l.length && j < r.length) {
            if (l[i].grade <= r[j].grade) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < l.length) {
            a[k++] = l[i++];
        }
        while (j < r.length) {
            a[k++] = r[j++];
        }
    
    }
    
    public static void addStudents(Student [] s){
        
        for (int i=0; i<20; i++) {
        s[i]=new Student();
        }
        
        s[0].name="Ahmed  ";s[1].name="Hegazy ";s[2].name="Mohamed";s[3].name="ashraf ";s[4].name="nader  ";
        s[5].name="ibrahim";s[6].name="sayed  ";s[7].name="omar   ";s[8].name="shafy  ";s[9].name="farah  ";
        s[10].name="maram  ";s[11].name="khaled ";s[12].name="youmna ";s[13].name="dalia  ";s[14].name="radwa  ";
        s[15].name="essam  ";s[16].name="maiar  ";s[17].name="shaza  ";s[18].name="ehab   ";s[19].name="mahmoud";
        
        for (int i=0; i<20; i++) {
            s[i].ID = rand.nextInt((73900 - 73700) + 1) + 73700;
            s[i].grade = rand.nextInt((100 - 50) + 1) + 50;
        }
    }
    
    public static void viewStudents(Student [] s){
        
        System.out.print("name    ID    grade\n\n");
        for (int i=0; i<20; i++) {
        System.out.print(s[i].name+"    "+s[i].ID+"    "+s[i].grade+"\n");
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        Student [] s = new Student[20];
        
        addStudents(s);
        viewStudents(s);
      
        long startTime = System.currentTimeMillis();
        Student [] sub1= new Student[s.length/2];
        Student [] sub2= new Student[s.length-s.length/2];
        System.arraycopy(s, 0, sub1, 0, s.length/2);
        System.arraycopy(s, s.length/2, sub2, 0, s.length - s.length/2);
        
        Merge thread1 =new Merge(sub1);
        Merge thread2 =new Merge(sub2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        merge (s,thread1.getInternal(), thread2.getInternal());
        
        viewStudents(s);
    
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("2-thread MergeSort takes: " + (float)elapsedTime/1000 + " seconds");
    }
}

