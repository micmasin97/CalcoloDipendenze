package percorsidipendenze;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainClass
{
	public static void main(String[] args)
	{
		List<Task> listaTask = new ArrayList<Task>();
		try
		{
			if (args.length % 2 == 0)
			{
				for (int i = 0; i < args.length; i = i + 2)
				{
					Task task = new Task();
					task.setPeso(Integer.parseInt(args[i]));
					task.setDipendenza(Integer.parseInt(args[i + 1]));
					if (listaTask.size() > task.getDipendenza() || task.getDipendenza() == -1)
					{
						listaTask.add(task);
					} else
					{
						System.out.println("task non inseribile, dipendenza non rintracciabile");
					}
				}
				int pesoMax = 0;
				int idTask = 0;
				int i = 0;
				for (Task t : listaTask)
				{
					int peso = calcoloPeso(listaTask, i);
					if(peso>pesoMax)
					{
						pesoMax=peso;
						idTask=i;
					}
					i++;
				}
				System.out.println("il percorso massimo è quello del task con id "+idTask + " e vale: "+pesoMax);
			} else
			{
				System.out.println("numero errato di argomenti( gli argomenti vanno inseriti nell'ordine peso/dipendenza)");
			}
		} catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
	}

	private static int calcoloPeso(List<Task> listaTask, int i)
	{
		Task task = listaTask.get(i);
		if (task.getDipendenza() == -1)
		{
			return task.getPeso();
		} else
		{
			return task.getPeso() + calcoloPeso(listaTask, task.getDipendenza());
		}
	}
}
