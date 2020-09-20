class Untitled {
	public static void main(String[] args){
		int i = 3;
		int j = 1;
		while(i <= 9){
			if(i==3&&j==1||i==5&&j==1){
				System.out.println("<" + i + "단>");
			}
			System.out.println(i + " * " + j + " = " + (i*j));
			j++;
			if(j>9){
				i++;
				j = 1;
				System.out.println();
			}
		}
	}
}