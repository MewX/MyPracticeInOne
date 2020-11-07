package account

import "sync"

// Account stores the bank account's status.
type Account struct {
	balance int64
	active  bool
	mutex   sync.Mutex
}

// Open an account with initial deposit.
func Open(initialDeposit int64) *Account {
	if initialDeposit < 0 {
		return nil
	}
	return &Account{
		balance: initialDeposit,
		active:  true,
	}
}

// Close an account.
func (acc *Account) Close() (payout int64, ok bool) {
	acc.mutex.Lock()
	defer acc.mutex.Unlock()

	defer func() {
		acc.balance = 0
		acc.active = false
	}()
	return acc.balance, acc.active
}

// Balance returns the account balance.
func (acc *Account) Balance() (balance int64, ok bool) {
	acc.mutex.Lock()
	defer acc.mutex.Unlock()
	return acc.balance, acc.active
}

// Deposit will try to deposit/withdraw balance from account.
func (acc *Account) Deposit(amount int64) (newBalance int64, ok bool) {
	acc.mutex.Lock()
	defer acc.mutex.Unlock()

	newBalance = acc.balance + amount
	if !acc.active || newBalance < 0 {
		return acc.balance, false
	}
	acc.balance = newBalance
	return acc.balance, true
}
