Atone Con Android Team
-----------------

[![Build Status](https://circleci.com/gh/AsianTechInc/Atonecon-Android/tree/master.svg?style=shield&circle-token=47ad1bba2de729235789563ac4826028328afa82)](https://circleci.com/gh/AsianTechInc/Atonecon-Android/tree/master.svg?style=shield&circle-token=47ad1bba2de729235789563ac4826028328afa82)

General Workflow
-----------------
**Table of Contents**

- [1. Development](#1-development)
  - [1.1. Branch](#11-branch)
  - [1.2. Task, Issue](#12-task-issue)
    - [1.2.1. Milestones (required)](#121-milestones-required)
    - [1.2.2. Type Label (required)](#122-type-label-required)
    - [1.2.3. Rank Label (optional)](#123-rank-label-optional)
    - [1.2.4. Version Label (optional)](#124-version-label-optional)
    - [1.2.5. Status (required)](#125-status-required)
    - [1.2.6. Pull Request](#126-pull-request)
      - [1.2.6.1. Labeling](#1261-labeling)
      - [1.2.6.2. Code Review](#1262-code-review)
- [2. Release](#2-release)
  - [2.1. Release Notes](#21-release-notes)
  - [2.2 Versioning Convention](#22-versioning-convention)
- [3. Test Case](#3-test-case)
- [4. Deploy Rule](#4-deploy-rule)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## 1. Development

### 1.1. Branch

> PR: Pull Request

- **master**
  - always deployable
  - do not commit/push directly to this branch
  - must be protected by GitHub protected branches function

- **feature/xxx**
  - is created from **master**
  - *xxx* is ID backlog, Redmine, Github Issues, ...
  - PR must be created as soon as the branch is created
	  - in the creation, PR must have *wip* prefix, which would be removed after the PR is completed
    	- must follow the **master**
  - ex: `feature/timeline`

- **feature/xxx_yyy**
  	- is created from **feature/xxx**
  	- *yyy* is sub-feature of *xxx*
	- PR must be created as soon as the branch is created
  		- in the creation, PR must have *wip* prefix, which would be removed after the PR is completed
	  	- must follow the **feature/xxx**
  	- ex: `feature/timeline_header`

- **bugfix/xxx_desc**
  - is created from **master**
  - *xxx* is ID of issue
  - *desc* is issue's short description
  - PR must be created as soon as the branch is created
  - ex: `bugfix/123_login`

- **test/xxx**
	- is created from **master**
	- *xxx* is
	- PR must be created as soon as the branch is created
	- ex: `test/timeline-ui`

- **release/xxx**
  - is created from **master**
  - *xxx* is the released version
  - is created if a released version need maintain
  - ex: `release/1.0.0`

- **hotfix/yyy_desc**
  - is created from **release/xxx**
  - *yyy* is ID of issue
  - PR must be created as soon as the branch is created
  - send PR to **release/xxx** after finishing
  - open PR to **master** if needed
  - ex: `hotfix/456_logout`

- **feature, bugfix, hotfix, test** which is belong to one application only
  - will be added prefix `<app_code>/` where `<app_code>` is app code: `cm, ks, lv, rn`, for example:
	- `cm/feature/timeline_question`
	- `ks/bugfix/login_token`
  - or mutiple platforms:
	- `ios/feature/timeline_question`
	- `android/bugfix/login_token`

### 1.2. Task, Issue

#### 1.2.1. Milestones (required)

- **Sprint 1**
	- New features and implementation to be done for Sprint 1
	- Maintenance bugs related to Sprint 1 after release
- **Sprint 2**
	- New features and implementation to be done for Sprint 2
	- Maintenance bugs related to Sprint 2 after release
- ...
- **Sprint N** - final
	- New features and implementation to be done for Sprint N-1
	- Maintenance support period. Bugs related to final major version release

#### 1.2.2. Type Label (required)

Only 1 per issue.

- **Task** is for task that affect directly the functions of a product for example: creating a new screen, database, connect bluetooth device, etc
- **Bug** is for bugs raised by *QC* or internal team
- **Client** is for bugs raised by the *Client*

#### 1.2.3. Rank Label (optional)

Only 1 per issue. Only required by **Bug** or **Client**.

- **Rank-S** - highest
	- feature: blocking issue, many other issues depend on it
	- bug: blocking major function
	- **must be fixed as soon as possible**
- **Rank-A**
	- feature: important feature on its own but may not have many dependencies
	- bug: important function and need to be fixed within 1 day
- **Rank-B**
	- feature: for a single screen
	- bug: should be fixed within 2 days
- **Rank-C**
	- feature: lower priority
	- bug: should be fixed within 4 days
- **Rank-D**
	- feature: lowest priority
	- bug: minor bugs, lowest priority, allowed time is 1 week

#### 1.2.4. Version Label (optional)

- Optional and only 1 at most
- Version labels is **required** for bugs or tasks raised in the Maintenance Milestone
- Bugs or tasks/features raised in other milestones do not need to have this label
	- for example: `1.0.2` belong to Sprint 1 Maintenance
- These versions are agreed by *Client* beforehand and managed by *PM/TL/BA*
- *Developer* does not change this label without confirming

#### 1.2.5. Status (required)

Only 1 per issue.

- **New**
	- Every issue should be always created with this status.
- **InProgress**
	- Once someone starts working on this issue, it must be in this state.
	- PM will use this state to check who's working on what at the moment.
	- This is set by the *Developer*
- **Fixed**
	- Once *Developer* has referenced this issue in a PR then it's set to **Fixed** by *Developer*
- **Reviewed**
	- Once someone reviewed and merged the PR into major branch (**master**) then the referenced issues must be set to **Reviewed** by the *Reviewer*
- **Reopened**
	- Issues deployed will be verified by *QC* and will be set to **Reopened** if it can not pass the *QC* check.
	- This status is treated similar to status **New** and will go through the steps like a new issue.
	- This status is set by *QC*.
- **Done**
	- If *QC* approves the issue then this issue is set to **Done**.
	- If it's inside a normal sprint milestone then it will be **Closed**
	- If it's inside a maintenance milestone then it will be closed once the release to production is finished by the *Deployer*
	- This is set by *QC*
- **Pending**
	- If a ticket is waited for answer from *Client* or *BA*
	- Once you have the answer back please update the status of this ticket right away
	- *BA* please update the answer in the comment section.
	- Avoid using this status and discuss with *BA/QC/PM/TL* right away.

**GitHub Applying**

| Label | Color |
| :--- | :---: |
| <span style="padding:4px;border-radius:2px;background-color:#60AA14;color:#fff;font-weight:bold;">type-Task</span> | `#60AA14` |
| <span style="padding:4px;border-radius:2px;background-color:#DD2E1F;color:#fff;font-weight:bold;">type-Bug</span> | `#DD2E1F` |
| <span style="padding:4px;border-radius:2px;background-color:#DD2E1F;color:#fff;font-weight:bold;">type-Client</span> | `#DD2E1F` |
| <span style="padding:4px;border-radius:2px;background-color:#EC640C;color:#fff;font-weight:bold;">Rank-A</span> | `#EC640C` |
| <span style="padding:4px;border-radius:2px;background-color:#EFB920;color:#fff;font-weight:bold;">Rank-B</span> | `#EFB920` |
| <span style="padding:4px;border-radius:2px;background-color:#FFF2B6;color:#fff;font-weight:bold;">Rank-C</span> | `#FFF2B6` |
| <span style="padding:4px;border-radius:2px;background-color:#CFEDFB;color:#fff;font-weight:bold;">Rank-D</span> | `#CFEDFB` |
| <span style="padding:4px;border-radius:2px;background-color:#DD2E1F;color:#fff;font-weight:bold;">Rank-S</span> | `#DD2E1F` |
| <span style="padding:4px;border-radius:2px;background-color:#60AA14;color:#fff;font-weight:bold;">s1-New</span> | `#60AA14` |
| <span style="padding:4px;border-radius:2px;background-color:#60AA14;color:#fff;font-weight:bold;">s2-InProgress</span> | `#60AA14` |
| <span style="padding:4px;border-radius:2px;background-color:#008CC9;color:#fff;font-weight:bold;">s3-Fixed</span> | `#008CC9` |
| <span style="padding:4px;border-radius:2px;background-color:#008CC9;color:#fff;font-weight:bold;">s4-Reviewed</span> | `#008CC9` |
| <span style="padding:4px;border-radius:2px;background-color:#DD2E1F;color:#fff;font-weight:bold;">s5-Reopened</span> | `#DD2E1F` |
| <span style="padding:4px;border-radius:2px;background-color:#008CC9;color:#fff;font-weight:bold;">s6-Done</span> | `#008CC9` |
| <span style="padding:4px;border-radius:2px;background-color:#D0D3D6;color:#fff;font-weight:bold;">s7-Pending</span> | `#D0D3D6` |

#### 1.2.6. Pull Request

##### 1.2.6.1. Labeling

- Every PR must be assigned to someone.
- Every PR must have:
	- 1+ status label
	- 1+ type label (multiple depends on referenced)
	- 1 rank label (get the highest rank of the referenced issues)
	- *Milestone* must be assigned (set/updated by *Developer/Reviewer*)

##### 1.2.6.2. Code Review

- *Developer* must make sure no crash error before assigning the PR to someone to review.
- *Developer* must comment directly on these comments if he refuse to fix.
- *Reviewer* in case you have finished reviewing, please reassign the PR back to the final reviewer below:
- Only *Reviewer* is allowed to merge/close.

## 2. Release

### 2.1. Release Notes

- List features, bug fixes included in this release.
	- Description
	- URL to GitHub issue
- Group critical items (that might affect other products into shared section of release notes)
- Deployment Datetime (or Submission datetime)
- Deployer
- Deployment issues (like server problems or some unwanted situation during deployment)

### 2.2 Versioning Convention

- `X.Y.Z(B)`
	- X: Major releases
	- Y: Minor releases, new functionality
	- Z: Bug-fixes releases
	- B: Build number
- ex:
	- `1.0.0(1)` The first time available on store.
	- `1.0.1(1)` Fix HealthKit issue
	- `1.1.0(1)` Introducing new functionality like video call
	- `2.0.0(1)` New UI/UX

## 3. Test Case

- Version target
- ex:
	- All test cases version `1.0.0` for iOS app release version `1.0.0`
	- iOS increase version `1.0.1` for fix some bugs. Test case has to update the test case release to iOS app `1.0.1` and increase test case version

## 4. Deploy Rule

- PM confirm and TL will responsible for deploy and submission
- Writing release note before deploy
- Deploy before 4pm, and Mon-Thu except for an emergency
