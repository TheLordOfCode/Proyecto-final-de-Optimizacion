package proyectoOptimizacion;

import lpsolve.LpSolve;
import lpsolve.LpSolveException;

public class modelo {
	double[] solucion = new double[2];
	String resumen= "";	
	String funcionObjetivo = "0 0";
	int contador = 0;

	public void resolver(String[][] modelo){

		
		String restricciones = "";
		try {
			
			int cantidadVariables = (modelo.length * 2)+2;			
			LpSolve solver = LpSolve.makeLp(0, cantidadVariables);
			for (int i = 0; i < modelo.length; i++) {
				funcionObjetivo = funcionObjetivo+" 1 1";
				//				System.out.println(generarRestrinciones((modelo.length * 2), "1 0", contador, 1)+" "+modelo[i][1]);
				solver.strAddConstraint(generarRestrinciones((modelo.length * 2), "1 0", contador, 1), LpSolve.GE,Integer.parseInt( modelo[i][1]));
				restricciones += "\n"+generarRestrinciones((modelo.length * 2), "1 0", contador, 1)+"\n";
				//				System.out.println(generarRestrinciones((modelo.length * 2), "1 0", contador, -1) +" "+modelo[i][1]);
				solver.strAddConstraint(generarRestrinciones((modelo.length * 2), "1 0", contador, -1), LpSolve.LE,Integer.parseInt( modelo[i][1]));
				restricciones += generarRestrinciones((modelo.length * 2), "1 0", contador, -1)+"\n";
				contador++;

				//				System.out.println(generarRestrinciones((modelo.length * 2), "0 1", contador, 1)+" "+modelo[i][2]);
				solver.strAddConstraint(generarRestrinciones((modelo.length * 2), "0 1", contador, 1), LpSolve.GE,Integer.parseInt( modelo[i][2]));
				restricciones += generarRestrinciones((modelo.length * 2), "0 1", contador, 1)+"\n";
				//				System.out.println(generarRestrinciones((modelo.length * 2), "0 1", contador, -1)+" "+modelo[i][2]);
				solver.strAddConstraint(generarRestrinciones((modelo.length * 2), "0 1", contador, -1), LpSolve.LE,Integer.parseInt( modelo[i][2]));
				restricciones += generarRestrinciones((modelo.length * 2), "0 1", contador, -1);
				contador++;
			}
			solver.strSetObjFn(funcionObjetivo);
			solver.solve();


			// print solution
			System.out.println("Value of objective function: " + solver.getObjective());
			double[] var = solver.getPtrVariables();
//			for (int i = 0; i < var.length; i++) {
//				System.out.println("Value of var[" + i + "] = " + var[i]);
//			}
			solucion[0] = var[0];
			solucion[1] = var[1];
			solver.setLpName("Hogwarts");
			solver.setColName(1, "W");
			solver.setColName(2, "Z");
			int p = 1;
		      int j = 1;
		      for (int i = 3; i < cantidadVariables+1; i++) {
		    	  if(i%2==0){
		    		  solver.setColName(i, "J"+j);
		    		  j++;
		    	  }else{
		    		  solver.setColName(i, "P"+p);
		    		  p++;
		    	  }
			}
			solver.setOutputfile("detalles.txt");			
			solver.printLp();	
			solver.printConstraints(1);			
			solver.printSolution(1);
			solver.printObjective();
			//solver.printTableau();
			solver.deleteLp();

		} catch (LpSolveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//		System.out.println(funcionObjetivo + " cantidad :"+cantidadVariables);

	}

	
	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	
	public double[] getSolucion() {
		return solucion;
	}

	public void setSolucion(double[] solucion) {
		this.solucion = solucion;
	}

	public String generarRestrinciones(int catidad,String dosPrimeros, int posicion, int numeroAInsertar){
		String restrincion= dosPrimeros;
		for (int i = 0; i < catidad; i++) {
			if(i == posicion){
				restrincion = restrincion + " "+numeroAInsertar;

			}else{
				restrincion = restrincion + " 0";
			}
		}
		return restrincion;
		//		solver.strAddConstraint("1 0 1 0 0 0 0 0", LpSolve.GE, 1);
		//		solver.strAddConstraint("1 0 -1 0 0 0 0 0", LpSolve.LE, 1);
	}


}
