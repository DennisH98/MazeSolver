
public class MainTester {
	
	int RobotDirection=0; // 0=North, 1=East, 2=South, 3=West

	// Start in the 0,0 Cell
	int StartPosRow=0; // Starting position
	int StartPosCol=0;

	int CurrentPosRow=StartPosRow; // Starting position
	int CurrentPosCol=StartPosCol;

	int TargetPosRow=3;
	int TargetPosCol=5;
	
	Cell Grid[][] = new Cell[4][6];
	int wasHere[][] = new int[4][6];
	//Maybe clear out was here
	int wasHere2[][] = new int[4][6];
	int correctPath[]= new int[24];
	//Maybe correct path 2

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		MainTester test = new MainTester();
		
		test.GridInit();
		test.WallGen();
		//System.out.println(test.Grid[0][2].EastWall);
		
		test.MazeMapReverse(test.RobotDirection, test.CurrentPosRow, test.CurrentPosCol,-1,-1,0);
	//	test.Grid[3][0].visited =1;
		
		
		for(int i=3;i>-1;i--){
			for(int j=0;j<6;j++){
				
				System.out.print(test.Grid[i][j].visited);
				
			}
			System.out.println();
		}
		System.out.println();
		for(int i=0;i<24;i++){
			
			System.out.print(test.correctPath[i]);
			
		}
	
		
		
		
	}
	
	int MazeMap(int position, int row, int col,int prevRow, int prevCol,int rightPath){

		// some ideas
		// Before when checking previous check other walls
		//if row > 3  or row< 0 out of bounds
		//col >5 or col< 5 out of bounds
		
		
		//Another array to help determine if cell was visited
		wasHere[row][col]= 1;
	
		//Base case (return when value found)
		if(row==TargetPosRow && col == TargetPosCol) {
			
			//Grid[row][col].visited= 1;
			System.out.println("Target Found");
			Grid[row][col].visited=1;
			return 1;
			
		}else {
			
			
		//Checks if a wall is north (0= no wall, 1 = wall)
		if(Grid[row][col].NorthWall ==0){
		 //go north
			
			
			if(position!= 0) {
				position =0;
			}
			
			correctPath[rightPath]= position;
			
			//System.out.println("North");
			
			
			//Checks to see if this is the first Cell	
			if (prevRow == -1 && prevCol == -1 ){
				prevRow = row;
				prevCol = col;
			
			//The First Recursion, Visits the next row 	
			 int value = MazeMap( position,  row+1,  col, prevRow, prevCol,rightPath+1);
			 
			 //First Recursion Finished!, Mark the first cell with 1
			 Grid[row][col].visited = 1;
			 
			 //value = 1 means that target was found, return and end the program
			 //If not, go and check other direction
			 if (value==1) {
				 return 1;
				 //End of program (*1) 
			 }
			 
			
			}else{
			//This is the else, it means that you are no longer in the first cell
			// Either went N or E or S or W
			// Checking your next direction
				
			//If the cell you are going back was the previous cell	
				if(row+1  == prevRow && col == prevCol){
					
					//if(Grid[row][col].EastWall==0) {
				//	}else {						
				//	}
					
					correctPath[rightPath]= -1;
					
				//Do nothing
			   //Don't go back to the cell you were just at
			   // Ensures that the other directions is checked
					
					
					
				}else{
					
					//Hit a dead end
					//Returned to a previous point to check other directions
					if(wasHere[row+1][col]==1) {
						correctPath[rightPath]= -1;
						return 0;
					}
					
					//Go north, assuming it wasn't visited
					int value =MazeMap( position,  row+1,  col, row, col, rightPath+1);
					
					//If value is 1, then target location was found 
					// Mark the path and return all the way to the end
					if(value ==1) {
						Grid[row][col].visited = 1;
						return 1;
						//This will return all the way to the First Recursion
					}
					
					//If the two other conditions above are not met
					// Check the other directions now
					
					
				}
				
				
			}
			
				//Grid[row][col].visited= MazeMap(position , row+1 , col);		
		}//End of the north
		
		//Same thing applies for the other directions
		//

		if(Grid[row][col].EastWall ==0){
		 //go east
			
			
			if(position!= 1) {
				position =1;
			}
			
			correctPath[rightPath]= position;
			
			//System.out.println("East");
			
			if (prevRow == -1 && prevCol == -1 ){
				prevRow = row;
				prevCol = col;
				
			 int value =MazeMap( position,  row,  col+1, prevRow, prevCol,rightPath +1);
			 Grid[row][col].visited = 1;
			 if(value==1) {
				 return 1;
			 }
			 //return 1;
			}else{
				
				if(row  == prevRow && col+1 == prevCol){
					
					correctPath[rightPath]= -1;
					
				}else{
					if(wasHere[row][col+1]==1) {
						correctPath[rightPath]= -1;
						return 0;
					}
					int value = MazeMap( position,  row,  col+1, row, col,rightPath +1);
					if(value ==1) {
						Grid[row][col].visited = 1;
						return 1;
					}
					
				}
				
				
			}
			
			//Grid[row][col].visited= MazeMap(position , row , col+1 );//?
			
		}

		if(Grid[row][col].SouthWall ==0 ){
			
		 
			if(position!= 2) {
				position =2;
			}
			
			correctPath[rightPath]= position;
			//System.out.println("South");
			
			if (prevRow == -1 && prevCol == -1 ){
				prevRow = row;
				prevCol = col;
				
			 int value =MazeMap( position,  row-1,  col, prevRow, prevCol, rightPath +1);
			 Grid[row][col].visited = 1;
			 if(value==1) {
				 return 1;
			 }
			 
			// return 1;
			}else{
				
				if(row - 1 == prevRow && col == prevCol){
				
					correctPath[rightPath]= -1;
					
				}else{
					if(wasHere[row-1][col]==1) {
						correctPath[rightPath]= -1;
						return 0;
					}
					
					int value = MazeMap( position,  row-1,  col, row, col, rightPath +1);
					if(value ==1) {
						Grid[row][col].visited = 1;
						return 1;
					}
				}
				
				
			}
			
			//Grid[row][col].visited= MazeMap(position , row-1 , col);
			
		}

		if(Grid[row][col].WestWall ==0 ){
		 //go west
			
			//System.out.println("West");
			if(position!= 3) {
				position =3;
			}
			
			correctPath[rightPath]= position;
			
			
			if (prevRow == -1 && prevCol == -1 ){
				prevRow = row;
				prevCol = col;
				
			 int value =MazeMap( position,  row,  col-1, prevRow, prevCol,rightPath +1);
			 Grid[row][col].visited = 1;
			 if (value ==1) {
				 return 1;
			 }
			
			}else{
				
				if(row == prevRow && col-1 == prevCol){
					
					correctPath[rightPath]= -1;
								
					 // returns 0;
				}else{
					
					if(wasHere[row][col-1]==1) {
						correctPath[rightPath]= -1;
						return 0;
					}
					 int value = MazeMap( position,  row,  col-1, row, col, rightPath+1); //(3,3)
					if(value ==1) {
						Grid[row][col].visited = 1;
						return 1;
					}
				}
				
				
			}
						
			//Grid[row][col].visited=MazeMap(position , row , col-1);//?
			
		}
		
		//Grid[row][col].visited=0;
		return 0; // can't go anywhere (dead end)
		
		}


		}
	
	
	int MazeMapReverse(int position, int row, int col,int prevRow, int prevCol,int rightPath){

		
		
		//Another array to help determine if cell was visited
		wasHere2[row][col]= 1;
	
		//Base case (return when value found)
		if(row==TargetPosRow && col == TargetPosCol) {
			
			//Grid[row][col].visited= 1;
			System.out.println("Target Found");
			Grid[row][col].visited=1;
			return 1;
			
		}else {
			
			
		//Checks if a wall is north (0= no wall, 1 = wall)
			if(Grid[row][col].WestWall ==0 ){
				 //go west
					
					//System.out.println("West");
					if(position!= 3) {
						position =3;
					}
					
					correctPath[rightPath]= position;
					
					
					if (prevRow == -1 && prevCol == -1 ){
						prevRow = row;
						prevCol = col;
						
					 int value =MazeMapReverse( position,  row,  col-1, prevRow, prevCol,rightPath +1);
					 Grid[row][col].visited = 1;
					 if (value ==1) {
						 return 1;
					 }
					
					}else{
						
						if(row == prevRow && col-1 == prevCol){
							
						    //Goes too far to dead end, correct it here
							correctPath[rightPath]= -1;
							
							 // returns 0;
						}else{
							
							if(wasHere2[row][col-1]==1) {
								correctPath[rightPath]= -1;
								return 0;
							}
							 int value = MazeMapReverse( position,  row,  col-1, row, col, rightPath+1); //(3,3)
							if(value ==1) {
								Grid[row][col].visited = 1;
								return 1;
							}
						}
						
						
					}
					
					
					
					//Grid[row][col].visited=MazeMap(position , row , col-1);//?
					
				}	
			
			if(Grid[row][col].SouthWall ==0 ){
				
				 
				if(position!= 2) {
					position =2;
				}
				
				correctPath[rightPath]= position;
				//System.out.println("South");
				
				if (prevRow == -1 && prevCol == -1 ){
					prevRow = row;
					prevCol = col;
					
				 int value =MazeMapReverse( position,  row-1,  col, prevRow, prevCol, rightPath +1);
				 Grid[row][col].visited = 1;
				 if(value==1) {
					 return 1;
				 }
				 
				// return 1;
				}else{
					
					if(row - 1 == prevRow && col == prevCol){
						
						//System.out.println("This occured");
						correctPath[rightPath]= -1;
						
					}else{
						if(wasHere2[row-1][col]==1) {
							correctPath[rightPath]= -1;
							return 0;
						}
						
						int value = MazeMapReverse( position,  row-1,  col, row, col, rightPath +1);
						if(value ==1) {
							Grid[row][col].visited = 1;
							return 1;
						}
					}
					
					
				}
				
				
				
				//Grid[row][col].visited= MazeMap(position , row-1 , col);
				
			}
		
		//Same thing applies for the other directions
		//

		if(Grid[row][col].EastWall ==0){
		 //go east
			
			
			if(position!= 1) {
				position =1;
			}
			
			correctPath[rightPath]= position;
			
			//System.out.println("East");
			
			if (prevRow == -1 && prevCol == -1 ){
				prevRow = row;
				prevCol = col;
				
			 int value =MazeMapReverse( position,  row,  col+1, prevRow, prevCol,rightPath +1);
			 Grid[row][col].visited = 1;
			 if(value==1) {
				 return 1;
			 }
			 //return 1;
			}else{
				
				if(row  == prevRow && col+1 == prevCol){
					
					
					correctPath[rightPath]= -1;
					
					
					
				}else{
					if(wasHere2[row][col+1]==1) {
						correctPath[rightPath]= -1;
						return 0;
					}
					int value = MazeMapReverse( position,  row,  col+1, row, col,rightPath +1);
					if(value ==1) {
						Grid[row][col].visited = 1;
						return 1;
					}
					
				}
				
				
			}
			
			
			
			//Grid[row][col].visited= MazeMap(position , row , col+1 );//?
			
		}

		
		
		
		if(Grid[row][col].NorthWall ==0){
			 //go north
				
				
				if(position!= 0) {
					position =0;
				}
				
				correctPath[rightPath]= position;
				
				//System.out.println("North");
				
				
				//Checks to see if this is the first Cell	
				if (prevRow == -1 && prevCol == -1 ){
					prevRow = row;
					prevCol = col;
				
				//The First Recursion, Visits the next row 	
				 int value = MazeMapReverse( position,  row+1,  col, prevRow, prevCol,rightPath+1);
				 
				 //First Recursion Finished!, Mark the first cell with 1
				 Grid[row][col].visited = 1;
				 
				 //value = 1 means that target was found, return and end the program
				 //If not, go and check other direction
				 if (value==1) {
					 return 1;
					 //End of program (*1) 
				 }
				 
				
				}else{
				//This is the else, it means that you are no longer in the first cell
				// Either went N or E or S or W
				// Checking your next direction
					
				//If the cell you are going back was the previous cell	
					if(row+1  == prevRow && col == prevCol){
						
						//if(Grid[row][col].EastWall==0) {
					//	}else {						
					//	}
						
						correctPath[rightPath]= -1;
					//Do nothing
				   //Don't go back to the cell you were just at
				   // Ensures that the other directions is checked
						
						
						
					}else{
						
						//Hit a dead end
						//Returned to a previous point to check other directions
						if(wasHere2[row+1][col]==1) {
							correctPath[rightPath]= -1;
							return 0;
						}
						
						//Go north, assuming it wasn't visited
						int value =MazeMapReverse( position,  row+1,  col, row, col, rightPath+1);
						
						//If value is 1, then target location was found 
						// Mark the path and return all the way to the end
						if(value ==1) {
							Grid[row][col].visited = 1;
							return 1;
							//This will return all the way to the First Recursion
						}
						
						//If the two other conditions above are not met
						// Check the other directions now
						
						
					}
					
					
				}
				
				
				
				
					//Grid[row][col].visited= MazeMap(position , row+1 , col);		
			}//End of the north

		
		
		//Grid[row][col].visited=0;
		return 0; // can't go anywhere (dead end)
		
		}


		}
	
	
	
	
	
	
	
	void GridInit(){
		for(int i=0;i<4;i++){
			for(int j=0;j<6;j++){
				
				Grid[i][j]= new Cell();
				Grid[i][j].NorthWall=0;
				Grid[i][j].EastWall=0;
				Grid[i][j].WestWall=0;
				Grid[i][j].SouthWall=0;
				wasHere[i][j]=0;
			}
		}
		
		for(int i=0;i<24;i++){
			
			 correctPath[i]= -1;
		}
	}
	
	void WallGen(){
		int i=0;
		int j=0;

		for(i=0;i<4;i++){
			Grid[i][0].WestWall=1;
			Grid[i][5].EastWall=1;
		}

		for(j=0;j<6;j++){
			Grid[0][j].SouthWall=1;
			Grid[3][j].NorthWall=1;
		}
	  //Row 1
		Grid[0][1].NorthWall = 1;
		Grid[0][1].EastWall = 1;
		Grid[0][2].WestWall =1;
		Grid[0][2].EastWall =1;
		Grid[0][3].WestWall =1;
		Grid[0][4].NorthWall=1;
		//Row 2
		Grid[1][0].NorthWall =1;
		Grid[1][1].SouthWall =1;
		Grid[1][3].NorthWall =1;
		Grid[1][3].EastWall =1;
		Grid[1][4].WestWall=1;
		Grid[1][4].SouthWall=1;
		Grid[1][5].NorthWall=1;
		//Row 3
		Grid[2][0].SouthWall =1;
		Grid[2][0].EastWall =1;
		Grid[2][1].WestWall = 1;
		Grid[2][1].EastWall =1;
		Grid[2][2].WestWall =1;
		Grid[2][3].NorthWall =1;
	  Grid[2][3].SouthWall =1;
	  Grid[2][5].NorthWall =1;
	  Grid[2][5].SouthWall =1;
	  //Row 4
	  Grid[3][1].EastWall =1;
	  Grid[3][2].WestWall =1;
	  Grid[3][3].SouthWall=1;
	  Grid[3][3].EastWall=1;
	  Grid[3][4].WestWall=1;
	  Grid[3][5].SouthWall=1;

	}

	
	

}
