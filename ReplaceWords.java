// Time Complexity : O(kl) + O(mn) where k is the number of words in dictionary and l is the average length of one word. We iterate over m words and then each character so n is the average length of one word
// Space Complexity : O(kl) + O(m) => Dictonary words occupy kl & string[] consiting of m words
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

/*
Approach: Using Trie. We will store the dictionary words in the Trie. Then iterate over the sentence and then go character by character, break if character doesn't exist or else move curr which is TrieNode starting from root.
 */
class Solution {

    class TrieNode {
        boolean isEnd;
        TrieNode[] children;

        public TrieNode() {
            isEnd = false;
            children = new TrieNode[26];
        }
    }

    TrieNode root;

    private void insert(String word) {

        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }

            curr = curr.children[c - 'a'];
        }
        curr.isEnd = true;
    }

    public String replaceWords(List<String> dictionary, String sentence) {

        root = new TrieNode();
        for (String str : dictionary) {
            insert(str);
        }
        String[] strArray = sentence.split(" ");
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < strArray.length; i++) {
            String str = strArray[i];
            TrieNode curr = root;
            StringBuilder sb = new StringBuilder();

            if (i != 0) {
                answer.append(" ");
            }
            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);

                if (curr.children[c - 'a'] == null || curr.isEnd) {
                    break;
                }

                curr = curr.children[c - 'a'];
                sb.append(c);
            }

            if (curr.isEnd) {
                answer.append(sb);
            } else {
                answer.append(str);
            }
        }

        return answer.toString();
    }
}