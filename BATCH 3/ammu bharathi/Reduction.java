import java.util.*;

public class Reduction 
{
	
	static void reduce(int[][] array, List<Integer> p, List<Integer> list)
	{
		int max = 0, index = -1;
		for(int i=0; i<array.length; i++)
		{
			int count = 0;
			if(list.contains(i))
			{
				continue;
			}
			for(int j=0; j<array[i].length; j++)
			{
				if(p.contains(array[i][j]))
				{
					count++;
				}
			}
			if(max < count)
			{
				max = count;
				index = i;
			}
		}
		list.add(index);
		p_red(array, index, p);
	}
	
	static void p_red(int[][] array, int index, List<Integer> p)
	{
		for(int i=0; i<array[index].length; i++)
		{
			int temp = array[index][i];
			if(p.contains(array[index][i]))
			{
				p.remove(new Integer(temp));
			}
		}
	}
	
	static int unique(int[][] array, int[] temp, int index, List<Integer> list)
	{
		int count = 0;
		for(int k=0; k<temp.length; k++)
		{
			int val = temp[k];
			boolean flag = true;
			for(int i=0; i<list.size(); i++)
			{
				if(i == index)
				{
					continue;
				}
				for(int j=0; j<array[list.get(i)].length; j++)
				{
					if(array[list.get(i)][j] == val)
					{
						flag = false;
						break;
					}
				}
				if(!flag)
				{
					break;
				}
			}
			if(flag)
			{
				count++;
			}
		}
		return count;
	}
	
	
	
	public static void main(String[] args) 
	{
		int[][] array = { {1,2,5,6,8,11,15,17},
						  {1,2,5,6,8,12,16,17},
						  {1,2,5,6,7,10,14,17},
						  {1,2,5,6,7,10,14,17},
						  {1,2,5,6,7,9,13,17},
						  {1,2,5,6,7,9,13,17},
						  {1,2,3,4,5,6,8,12,16,17}};
		ArrayList<Integer> p = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17));
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i=0; i<array.length; i++)
		{
			System.out.print("T"+(i+1)+" ");
			for(int j=0; j<array[i].length; j++)
			{
				System.out.print(array[i][j]);
				if(j+1 != array[i].length)
				{
					System.out.print(",");
				}
			}
			System.out.println();
		}
		
		int val = p.size();
		int index = -1;
		for(int i=0; i<array.length; i++)
		{
			if(array[i].length < val)
			{
				val = array[i].length;
				index = i;
			}
		}
		list.add(index);
		p_red(array, index, p);		
		while(p.size() != 0)
		{
			reduce(array, p, list);
		}
		System.out.println("Minimised Testcases are...");
		for(int i=0; i<list.size(); i++)
		{
			System.out.print("T"+(list.get(i)+1)+" ");
		}
		//System.out.println();
		
		
		// Unique Value Generator
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0; i<list.size(); i++)
		{
			int[] temp = array[list.get(i)].clone();
			int count = unique(array, temp, i, list);
			map.put(list.get(i), count);
		}
		//System.out.println(map);
		Map<Integer, Integer> reverseSortedMap = new LinkedHashMap<>();
		map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
					  .forEachOrdered(x-> reverseSortedMap.put(x.getKey(), x.getValue()));
		System.out.println();
		System.out.println("Unique coverage values of testcases with 0 indexed are");
		System.out.println(reverseSortedMap);
		System.out.println("Optimal Testsuite size...");
		for(Map.Entry m:reverseSortedMap.entrySet())
		{
			int key = (int)m.getKey();
			if(list.contains(key))
			{
				System.out.print("T"+(key+1)+" ");
			}
		}
	}
}
