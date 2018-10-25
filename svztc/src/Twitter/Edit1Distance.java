package Twitter;

/**
 Given a dictionary of words and a query, find all the words  in the dictionary within edit distance 1 of the query word.
 Edit distance 1 means at most 1 of
 a) insertion of a character
 b) deletion of a character and
 c) substitution of a character.



 1. Check if the expression is valid (simple valid  parenthesis problem)
 2. Generate valid permutations of expressions based on the count of parenthesis pair

 def bracketsPermutation(n):
    brackets(n, 0, "")
 def brackets(openStock, closeStock, s):
     if openStock is 0 and closeStock is 0:
        print s
     if openStock > 0:
        brackets(openStock-1, closeStock + 1, s + "[")
     if closeStock > 0:
        brackets(openStock, closeStock - 1, s + "]")

 3. Check if two strings are One Edit distance away.
 */
public class Edit1Distance {

    /*

    def in_distance(query_word, dictionary):
    for word in dictionary:
        l1, l2 = len(word), len(query_word)
        if abs(l1 - l2) <= 1:
            distance = 0
            i, j = 0, 0
            while i < l1 and j < l2 and distance <= 1:
                if word[i] is query_word[j]:
                    i += 1
                    j += 1
                else:
                    distance += 1
                    if l1 is not l2:
                        if l1 > l2:
                            i += 1
                        else:
                            j += 1
                    else:
                        i += 1
                        j += 1
            else:
                distance += (l1 - i - 1 + l2 - j - 1)
                if distance <= 1:
                    print word #print all words within 1 distance

        #driver function
        in_distance("internal", ["intergral", "infernal", "interal", "inteornal", "ianternal"])
     */
}
