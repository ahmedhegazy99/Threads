
package mergesort;


class Merge extends Thread{
   private final  Student [] internal;

    public Merge(Student []a) {
        internal=a;
    }
    public Student[] getInternal(){
        return internal;
    }
   
   public void mergeSort(Student [] a){
       if(a.length > 1){
           Student [] l= leftHalf(a);
           Student [] r= rightHalf(a);
           mergeSort(l);
           mergeSort(r);
           merge(a,l,r);
       }
   }
    public Student[] leftHalf(Student []a){
        int size=a.length/2;
        Student [] l=new Student [size];
       System.arraycopy(a, 0, l, 0, size);
        return l;
    }
    
    
    public Student [] rightHalf(Student []a){
        int size =a.length-a.length/2;
        Student [] r=new Student [size];
        for (int i = 0; i < size; i++) {
            r[i] = a[i + a.length/2];
        }
        return r;
    }
    
    public void merge(Student []result,Student []l,Student []r){
           int i = 0, j = 0, k = 0;
           while (i < l.length && j < r.length) {
               if (l[i].grade <= r[j].grade) {
                   result[k++] = l[i++];
               }
               else {
                   result[k++] = r[j++];
               }
           }
           while (i < l.length) {
               result[k++] = l[i++];
           }
           while (j < r.length) {
               result[k++] = r[j++];
           }
    }
    
   @Override
    public void run(){
        mergeSort(internal);
    }
}
