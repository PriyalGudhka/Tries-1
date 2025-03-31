// Time Complexity : O(nk) for Trie + O(nk) to search where n is the no.of words and k is the average length of each word
// Space Complexity : O(nk) for trie and O(nk) for queue
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

/*
Approach : Create a TrieNode class with word & children[] variables where word is used to store the word formed so far and children will carry the next letters of a word. Build a Trie by inserting all the words. Consider a root node of type TrieNode, for insert check if the children is not initialized, intialize it and move the curr node which starts from the root. Once the loop ends simply mark curr.word = word. To find the longest string start by adding the curr which is initialied with root TrieNode in the queue and then poll the curr element and iterate using a for loop starting from the last char 'z' as we are trying to build the lexicographically smallest string and check if that curr.children[i].word is not null add in the queue. At the end if curr.word is still null it indicates we are not able to form a string and return "" else simply return curr.word.
 */
class Solution {

    class TrieNode {

        TrieNode[] children;
        String word;

        public TrieNode() {

            children = new TrieNode[26];
        }
    }

    TrieNode root;

    public void isInsert(String word) {

        TrieNode curr = root;
        for (char ch : word.toCharArray()) {

            if (curr.children[ch - 'a'] == null) {
                curr.children[ch - 'a'] = new TrieNode();
            }
            curr = curr.children[ch - 'a'];
        }

        curr.word = word;
    }

    public String longestWord(String[] words) {

        root = new TrieNode();

        for (String word : words) {
            isInsert(word);
        }

        Queue<TrieNode> q = new LinkedList<>();
        TrieNode curr = root;
        q.add(curr);

        while(!q.isEmpty()){

            curr = q.poll();

            //As we want to build lexicographically smaller string
            for(int i = 25; i >= 0; i--){

                if(curr.children[i] != null && curr.children[i].word != null){
                    q.add(curr.children[i]);
                }
            }
        }

        return curr.word == null ? "" : curr.word;
    }
}