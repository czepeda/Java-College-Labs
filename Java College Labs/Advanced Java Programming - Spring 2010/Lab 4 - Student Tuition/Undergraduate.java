public class Undergraduate extends Graduate
	{
		public double tuition()
		{
			if(stcredits < 10)
			return stcredits*75;
else
if(stcredits <16)
return 875;
else
return 875+(stcredits-15)*75;
}
}




