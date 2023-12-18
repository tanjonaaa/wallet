#!/bin/bash

COMMITS=($(git rev-list saving~..HEAD))
for COMMIT in "${COMMITS[@]}"
do
    git filter-branch -f --env-filter '
    COMMIT_DATE=$(git log $GIT_COMMIT -n1 --format=%aD);
    NEW_DATE=$(date -d "$COMMIT_DATE-1 days 3 hours" -R);
    export GIT_COMMITTER_DATE="$NEW_DATE"
    export GIT_AUTHOR_DATE="$NEW_DATE"
    ' 2a9c94066dd185ca26da34c629eaa80a0f91250f..HEAD
done
