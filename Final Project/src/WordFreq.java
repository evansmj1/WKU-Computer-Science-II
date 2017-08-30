//----------------------------------------------------------------------------
// WordFreq.java              by Dale/Joyce/Weems                    Chapter 8
//
// Defines word-frequency pairs
//----------------------------------------------------------------------------


import java.text.DecimalFormat;

public class WordFreq implements Comparable<WordFreq>
{
  private char word;
  private int freq;

  DecimalFormat fmt = new DecimalFormat("00000");


  public WordFreq(char newWord)
  {
    word = newWord;
    freq = 0;
   }

  public void inc()
  {
    freq++;
  }

  public int compareTo(WordFreq other)
  {
    return (this.word+"").compareTo(""+other.word); 
  }

  public String toString()
  {
    return(fmt.format(freq) + " " + word);
  }

  public char wordIs()
  {
    return word;
  }

  public int freqIs()
  {
    return freq;
  }
}
 