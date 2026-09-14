/*
1. O(1), 1 pass
int n = 16;
int pass = 0;
for(int i=0; i<1; i++){
    pass++;			
    System.out.println("pass count: " + pass);
}



2. O(n), 7 passes
int n = 7;
int pass = 0;
for(int i=0; i<n; i++){
	pass++;
    System.out.println("pass count: " + pass);
}


3. O(n), 6 passes
int n = 3;
int pass = 0;
for(int i=0; i<n; i++){
	for(int j=0; j<2; j++){
		pass++;
        System.out.println("pass count: " + pass);
	}
}




4. O(logn), 4 passes

		int n = 16;
		int pass = 0;
		for(int i=n; i>1; i/=2){
			pass++;
			System.out.println("pass count: " + pass);
		}




5. O(logn), 5 passes
		int n = 32;
		int pass = 0;
		for(int i=1; i<n; i*=2){
			pass++;
System.out.println("pass count: " + pass);
		}


6. O(nlogn), 24 passes
		int n = 8;
		int pass = 0;
		for(int i=0; i<n; i++){
			for(int j=1; j<n; j*=2){
				pass++;
System.out.println("pass count: " + pass);
			}
		}


7. O(nlogn), 8 passes
		int n = 4;
		int pass = 0;
		for(int i=1; i<n; i*=2){
			for(int j=0; j<n; j++){
				pass++;
				System.out.println("pass count: " + pass);
			}
		}



8. O(n^2), 25 passes
		int n = 5;
		int pass = 0;
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				pass++;
				System.out.println("pass count: " + pass);

			}
		}


9. O(n^3), 27 passes
		int n = 3;
		int pass = 0;
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				for(int k=0; k<n; k++){
					pass++;
					System.out.println("pass count: " + pass);
				}
			}
		}






 */