#include <stdio.h>
#include <stdlib.h>

typedef char ElemType;
typedef struct BiTNode
{
ElemType data;
struct BiTNode *lchild, *rchild;
}BiTNode, *BiTree;

void creatBiTree(BiTree *T) {
    ElemType c=1;
  //  scanf("%c", &c);
    if ('#' == c)
    {
        *T = NULL;
    }
    else
    {
        *T = (BiTNode *)malloc(sizeof(BiTNode));
        (*T)->data = c;
 //       creatBiTree(&(*T)->lchild);
//        creatBiTree(&(*T)->rchild);
    }
}

void copybitree(BiTree T, BiTree *newT) {
    if (T == NULL)
    {
        *newT = NULL;
        return;
    }
    else
    {
        *newT = (BiTNode *)malloc(sizeof(BiTNode));
        ((*newT)->data) = (T->data);
        copybitree(T->lchild, &(*newT)->lchild);
        copybitree(T->rchild, &(*newT)->rchild);
    }
}
void preorder(BiTree T) {
    if (T) {
        printf("%c\n", T->data);
        preorder(T->lchild);
        preorder(T->rchild);
    }
}
void main() {
	puts("hellolllllll");
    BiTree T=NULL,newT=NULL;
    creatBiTree(&T);
	printf("Ç°Ðò±éÀú\n");
	preorder(T);
    copybitree(T, &newT);
	preorder(newT);
}