public class Graduate
{
String title, stno, stname, ststanding;
int stcredits;
public double tuition()
{
	if(stcredits < 7)
	return stcredits * 125;
	else
	if(stcredits < 13)
	return 1575;
	else
	return 1575 + (stcredits - 12) * 125;
}
}

